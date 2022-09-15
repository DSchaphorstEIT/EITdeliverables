package com.eitcat.dschaphorst_p3_music.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.databinding.SongCardBinding
import com.squareup.picasso.Picasso

class HomeAdapter(
    private val songsDataSet: MutableList<Song> = mutableListOf(),
    private val onSongClickHandler: (Song) -> Unit
    ) : RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder(
            SongCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bind(songsDataSet[position], onSongClickHandler)

    override fun getItemCount(): Int = songsDataSet.size

    fun setData(songs: List<Song>){
        songsDataSet.clear()
        songs.forEach{
            songsDataSet.add(it)
        }
        notifyDataSetChanged()
    }
}

class SongViewHolder(private val binding: SongCardBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(song: Song, onSongClickHandler: (Song) -> Unit) {
        binding.cardTrackName.text = song.trackName
        binding.cardArtistName.text = song.artistName
        Picasso.get()
            .load(song.artworkUrl60)
            .placeholder(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.cardCoverArt)

        binding.root.setOnClickListener{
            onSongClickHandler.invoke(song)
        }
    }
}
