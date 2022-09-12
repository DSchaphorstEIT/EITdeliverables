package com.eitcat.dschaphorst_p3_music.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.databinding.FragmentHomeBinding
import com.eitcat.dschaphorst_p3_music.ui.songDetails.SongDetailsViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeAdapter by lazy {
        HomeAdapter() {
            ViewModelProvider(requireActivity())[SongDetailsViewModel::class.java].curSong = it
            binding.root.findNavController().navigate(R.id.action_nav_home_to_songDetailsFragment)
            Toast.makeText(context,
                "Now Playing: ${it.trackName} by ${it.artistName}",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        homeViewModel.songsDataSet.observe(viewLifecycleOwner) { songs ->
            homeAdapter.setData(songs)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}