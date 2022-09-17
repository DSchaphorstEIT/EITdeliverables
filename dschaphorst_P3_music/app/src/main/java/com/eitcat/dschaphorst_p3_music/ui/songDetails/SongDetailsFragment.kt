package com.eitcat.dschaphorst_p3_music.ui.songDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.databinding.FragmentHomeBinding
import com.eitcat.dschaphorst_p3_music.databinding.FragmentSongDetailsBinding
import com.eitcat.dschaphorst_p3_music.ui.home.HomeViewModel
import com.google.android.exoplayer2.MediaItem
import com.squareup.picasso.Picasso

/**
 * The fragment used to see the details of the specific song, and is where
 * the user adds a song to the player's queue.
 *
 */
class SongDetailsFragment : Fragment() {

    private var _binding: FragmentSongDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * When fragment is created, populate the song's information on the view and set the
     * button to add the currently selected song to the player.
     *
     * @param inflater Used to inflate the fragment
     * @param container The containing ViewGroup
     * @param savedInstanceState Use if needing to save instance during UI changes
     * @return binding.root
     */
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

        binding.btnPlay.setOnClickListener {
            homeViewModel.addSongToQueue()
            homeViewModel.play()
            Toast.makeText(context,
                "Added to queue: ${curSong.trackName} by ${curSong.artistName}",
                Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    /**
     * Must remove _binding to prevent memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}