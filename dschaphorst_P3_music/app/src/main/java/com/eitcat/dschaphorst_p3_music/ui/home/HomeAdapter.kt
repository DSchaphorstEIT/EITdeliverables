package com.eitcat.dschaphorst_p3_music.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p3_music.R
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.databinding.SongCardBinding
import com.squareup.picasso.Picasso

/**
 * The adapter class that is used to control the RecyclerView
 *
 * @property songsDataSet MutableList of Songs that will be used to populate the view.
 * @property onSongClickHandler High order function used to open the selected song's details.
 */
class HomeAdapter(
    private val songsDataSet: MutableList<Song> = mutableListOf(),
    private val onSongClickHandler: (Song) -> Unit
    ) : RecyclerView.Adapter<SongViewHolder>() {

    /**
     * Create the [RecyclerView.ViewHolder] that is needed to populate the view.
     *
     * @param parent The containing ViewGroup
     * @param viewType Type of View
     * @return The [SongViewHolder] that will be used to bind the data.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder(
            SongCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    /**
     * Function used to call the binding
     *
     * @param holder The [SongViewHolder] reference that is being used to bind.
     * @param position The [position] of the current item cursor.
     */
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bind(songsDataSet[position], onSongClickHandler)

    /**
     * Get the size of the [songsDataSet]
     *
     * @return Size of [songsDataSet]
     */
    override fun getItemCount(): Int = songsDataSet.size

    /**
     * Sets the data for the [RecyclerView]. This should be the main method that gets called from
     * the fragment that is populating the data. First clears the [songsDataSet] before adding
     * each new item to the list.
     *
     * @param songs List of [Song] that will be displayed to the user.
     */
    fun setData(songs: List<Song>){
        songsDataSet.clear()
        songs.forEach{
            songsDataSet.add(it)
        }
        notifyDataSetChanged()
    }
}

/**
 * [RecyclerView.ViewHolder] that is used to bind the data to the view.
 *
 * @property binding The [SongCardBinding] for the view that is calling the adapter.
 */
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
