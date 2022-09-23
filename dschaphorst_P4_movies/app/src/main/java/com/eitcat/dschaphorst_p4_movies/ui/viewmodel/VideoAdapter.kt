package com.eitcat.dschaphorst_p4_movies.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p4_movies.data.model.Video
import com.eitcat.dschaphorst_p4_movies.databinding.VideoCardBinding

/**
 * The [RecyclerView.Adapter] used to populate a [RecyclerView] with the given [List] of [Video]
 * Originally, I was going to make this [VideoAdapter] as an addition to the [MovieAdapter]
 * but felt that for now, giving it it's own class would be better.
 *
 * @property videoDataSet Holds the [List] of [Video] items.
 * @property onVideoClickHandler Caller to be invoked when the [RecyclerView] item is clicked.
 */
class VideoAdapter(
    private val videoDataSet: MutableList<Video> = mutableListOf(),
    private val onVideoClickHandler: (Video) -> Unit
) : RecyclerView.Adapter<VideoViewHolder>() {
    /**
     * Create the [RecyclerView.ViewHolder] that is needed to populate the view.
     *
     * @param parent The containing ViewGroup
     * @param viewType Type of View
     * @return The [VideoViewHolder] that will be used to bind the data.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
        VideoViewHolder(
            VideoCardBinding.inflate(
                LayoutInflater.from(parent.context),
            parent, false))

    /**
     * Function used to call the binding of data to view.
     *
     * @param holder The [VideoViewHolder] reference that is being used to bind data.
     * @param position The [position] of the current item cursor.
     */
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoDataSet[position], onVideoClickHandler)
    }

    /**
     * @return Size of [videoDataSet]
     */
    override fun getItemCount(): Int = videoDataSet.size

    /**
     * Sets the data for the [RecyclerView]. This should be the main method that gets called from
     * the fragment that is populating the data. First clears the [videoDataSet] before adding
     * each new item to the list.
     *
     * @param videos List of [Video] that will be displayed to the user.
     */
    fun setVideoData(videos: List<Video>){
        videoDataSet.clear()
        videos.forEach{
            videoDataSet.add(it)
        }
        notifyDataSetChanged()
    }
}

/**
 * [RecyclerView.ViewHolder] that is used to bind the data to the view.
 *
 * @property binding The [VideoCardBinding] for the view that is calling the adapter.
 */
class VideoViewHolder (private val binding: VideoCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(video: Video, onVideoClickHandler: (Video) -> Unit) {
//        binding.moviTitle.text = movie.title

        binding.videoTitle.text = video.name
        binding.root.setOnClickListener{ onVideoClickHandler.invoke(video) }
    }
}