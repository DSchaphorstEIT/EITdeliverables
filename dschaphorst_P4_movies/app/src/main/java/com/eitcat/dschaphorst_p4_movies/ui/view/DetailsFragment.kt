package com.eitcat.dschaphorst_p4_movies.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p4_movies.R
import com.eitcat.dschaphorst_p4_movies.databinding.FragmentDetailsBinding
import com.eitcat.dschaphorst_p4_movies.databinding.FragmentNowBinding
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieViewModel
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.VideoAdapter
import com.eitcat.dschaphorst_p4_movies.util.UIState

const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val movieViewModel by lazy {
        ViewModelProvider(requireActivity())[MovieViewModel::class.java]
    }

    private val videoAdapter: VideoAdapter by lazy {
        VideoAdapter() {
            // Use the ExoPlayer to play the selected video.
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.detailsTitle.text = movieViewModel.curMovie?.title ?: "Title Missing"

        binding.detailsRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = videoAdapter
        }

        movieViewModel.movieLoadStatus.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.detailsRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.detailsRecycle.visibility = View.VISIBLE
                    Log.d(TAG, "onCreateView: ${state.videos}")
                    movieViewModel.videoHistList = state.videos
                    videoAdapter.setVideoData(movieViewModel.videoHistList)
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.detailsRecycle.visibility = View.GONE
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error Loading Videos")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Retry") { dialog, _ ->
                            movieViewModel.pullVideoData()
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        if (movieViewModel.videoHistList.isEmpty()) {
            movieViewModel.pullVideoData()
        } else {
            videoAdapter.setVideoData(movieViewModel.videoHistList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}