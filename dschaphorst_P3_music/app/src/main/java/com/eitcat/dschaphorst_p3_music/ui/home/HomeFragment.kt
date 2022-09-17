package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.databinding.FragmentHomeBinding
import com.eitcat.dschaphorst_p3_music.util.UIState

/**
 * This is the primary home fragment that will be displaying the default music search.
 *
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeAdapter by lazy {
        HomeAdapter() {
            ViewModelProvider(requireActivity())[HomeViewModel::class.java].curSong = it
            binding.root.findNavController().navigate(R.id.action_nav_home_to_songDetailsFragment)
        }
    }

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    /**
     * When fragment is created, create the [HomeAdapter] for the RecyclerView and set the
     * observable action that is dependant on the current [UIState].
     *
     * @param inflater Used to inflate the fragment
     * @param container The containing ViewGroup
     * @param savedInstanceState Use if needing to save instance during UI changes
     * @return binding.root
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

        homeViewModel.musicStatus.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.homeRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.homeRecycle.visibility = View.VISIBLE
                    homeViewModel.songHistList = state.songs
                    homeAdapter.setData(homeViewModel.songHistList)
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.homeRecycle.visibility = View.GONE
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error Loading Music")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Retry") { dialog, _ ->
                            homeViewModel.pullMusicData()
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        if (homeViewModel.songHistList.isEmpty()) {
            homeViewModel.pullMusicData()
        } else {
            homeAdapter.setData(homeViewModel.songHistList)
        }

        return binding.root
    }

    /**
     * Checks the network's internet connection, and switches to RoomDB if lost.
     */
    override fun onResume() {
        super.onResume()
        if(!homeViewModel.networkState.checkInternetConnection()){
            homeViewModel.addCurrentToDatabase()
            homeViewModel.getFromDatabase().observe(viewLifecycleOwner) {
                homeAdapter.setData(it)
            }
            AlertDialog.Builder(context)
                .setTitle("Error Occurred")
                .setMessage("Internet connection lost, using local data.")
                .setNegativeButton("DISMISS") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        } else {
            homeAdapter.setData(homeViewModel.songHistList)
        }
    }

    /**
     * Must remove _binding to prevent memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}