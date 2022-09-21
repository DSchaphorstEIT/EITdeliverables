package com.eitcat.dschaphorst_p4_movies.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p4_movies.databinding.FragmentHomeBinding
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieAdapter
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieViewModel
import com.eitcat.dschaphorst_p4_movies.util.UIState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val movieViewModel by lazy {
        ViewModelProvider(requireActivity())[MovieViewModel::class.java]
    }

    private val movieAdapter by lazy {
        MovieAdapter() {
            ViewModelProvider(requireActivity())[MovieViewModel::class.java].curMovie = it

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val apiCaller = movieViewModel.availableApiCalls[0]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }

        movieViewModel.movieLoadStatus.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.homeRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.homeRecycle.visibility = View.VISIBLE
                    movieViewModel.movieHistList = state.movies
                    movieAdapter.setData(movieViewModel.movieHistList)
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
            movieAdapter.setData(movieViewModel.movieHistList)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (!movieViewModel.networkStatus.checkInternetConnection() && movieViewModel.movieHistList.isEmpty()){
            AlertDialog.Builder(context)
                .setTitle("Network Lost")
                .setMessage("Network connection lost, and no data was obtained. Cannot display movie data until connection has been restored.")
                .setNegativeButton("DISMISS") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        } else {
            movieAdapter.setData(movieViewModel.movieHistList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}