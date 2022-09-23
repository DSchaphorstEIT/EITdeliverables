package com.eitcat.dschaphorst_p4_movies.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p4_movies.R
import com.eitcat.dschaphorst_p4_movies.databinding.FragmentNowBinding
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieAdapter
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieViewModel
import com.eitcat.dschaphorst_p4_movies.util.UIState

class NowFragment : Fragment() {

    private var _binding: FragmentNowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val movieViewModel by lazy {
        ViewModelProvider(requireActivity())[MovieViewModel::class.java]
    }

    private val movieAdapter by lazy {
        MovieAdapter() {
            movieViewModel.curMovie = it
            binding.root.findNavController().navigate(R.id.action_navigation_now_to_detailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val apiCaller = movieViewModel.availableApiCalls[1]

        _binding = FragmentNowBinding.inflate(inflater, container, false)

        binding.nowRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }

        movieViewModel.movieLoadStatus.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.nowRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.nowRecycle.visibility = View.VISIBLE
                    movieViewModel.movieHistList = state.movies
                    movieAdapter.setMovieData(movieViewModel.movieHistList)
                }
                is UIState.ERROR -> {

                    binding.loadingSpinner.visibility = View.GONE
                    binding.nowRecycle.visibility = View.GONE
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error Loading Movies")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Retry") { dialog, _ ->
                            movieViewModel.pullMovieData(apiCaller)
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        if (movieViewModel.movieHistList.isEmpty()) {
            movieViewModel.pullMovieData(apiCaller)
        } else {
            movieAdapter.setMovieData(movieViewModel.movieHistList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}