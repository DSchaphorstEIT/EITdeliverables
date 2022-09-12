package com.eitcat.dschaphorst_p3_music.ui.songDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eitcat.dschaphorst_p3_music.R

class SongDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SongDetailsFragment()
    }

    private lateinit var viewModel: SongDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SongDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}