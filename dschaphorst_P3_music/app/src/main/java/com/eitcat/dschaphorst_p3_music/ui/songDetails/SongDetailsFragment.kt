package com.eitcat.dschaphorst_p3_music.ui.songDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.databinding.FragmentHomeBinding
import com.eitcat.dschaphorst_p3_music.databinding.FragmentSongDetailsBinding
import com.eitcat.dschaphorst_p3_music.ui.home.HomeViewModel
import com.squareup.picasso.Picasso

class SongDetailsFragment : Fragment() {

    private var _binding: FragmentSongDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        _binding = FragmentSongDetailsBinding.inflate(inflater, container, false)

        val curSong = homeViewModel.curSong ?: Song(0)
        binding.displayTrackName.text = curSong.trackName
        binding.displayArtistName.text = curSong.artistName

        Picasso.get()
            .load(curSong.artworkUrl100)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.detailsCoverArt)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}