package com.eitcat.dschaphorst_p3_music.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.databinding.FragmentSearchBinding
import com.eitcat.dschaphorst_p3_music.ui.home.HomeAdapter
import com.eitcat.dschaphorst_p3_music.ui.home.HomeViewModel
import com.eitcat.dschaphorst_p3_music.util.UIState

const val TAG = "SearchFragment"

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeAdapter by lazy {
        HomeAdapter() {
            ViewModelProvider(requireActivity())[HomeViewModel::class.java].curSong = it
            binding.root.findNavController().navigate(R.id.action_nav_search_to_songDetailsFragment)
        }
    }

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

        homeViewModel.musicStatus.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.searchRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.searchRecycle.visibility = View.VISIBLE
                    homeViewModel.songHistList = state.songs
                    homeAdapter.setData(homeViewModel.songHistList)
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.searchRecycle.visibility = View.GONE
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

        binding.searchText.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                homeViewModel.pullMusicData(p0 ?: "")
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        } )

        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}