package com.eitcat.dschaphorst_p4_movies.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p4_movies.data.model.Movie
import com.eitcat.dschaphorst_p4_movies.databinding.MovieCardBinding

/**
 * The [RecyclerView.Adapter] used to populate a [RecyclerView] with the given [List] of [Movie]
 *
 * @property movieDataSet Holds the [List] of [Movie] items.
 * @property onMovieClickHandler Caller to be invoked when the [RecyclerView] item is clicked.
 */
class MovieAdapter(
    private val movieDataSet: MutableList<Movie> = mutableListOf(),
    private val onMovieClickHandler: (Movie) -> Unit
    ) : RecyclerView.Adapter<MovieViewHolder>() {
    /**
     * Create the [RecyclerView.ViewHolder] that is needed to populate the view.
     *
     * @param parent The containing ViewGroup
     * @param viewType Type of View
     * @return The [MovieViewHolder] that will be used to bind the data.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(MovieCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    /**
     * Function used to call the binding of data to view.
     *
     * @param holder The [MovieViewHolder] reference that is being used to bind data.
     * @param position The [position] of the current item cursor.
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieDataSet[position], onMovieClickHandler)
    }

    /**
     * @return Size of [movieDataSet]
     */
    override fun getItemCount(): Int = movieDataSet.size

    /**
     * Sets the data for the [RecyclerView]. This should be the main method that gets called from
     * the fragment that is populating the data. First clears the [movieDataSet] before adding
     * each new item to the list.
     *
     * @param movies List of [Movie] that will be displayed to the user.
     */
    fun setMovieData(movies: List<Movie>){
        movieDataSet.clear()
        movies.forEach{
            movieDataSet.add(it)
        }
        movieDataSet.sortByDescending { it.popularity }
        notifyDataSetChanged()
    }
}

/**
 * [RecyclerView.ViewHolder] that is used to bind the data to the view.
 *
 * @property binding The [MovieCardBinding] for the view that is calling the adapter.
 */
class MovieViewHolder (private val binding: MovieCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, onMovieClickHandler: (Movie) -> Unit) {
        binding.movieTitle.text = movie.title
        binding.popularity.text = "Pop: " + movie.popularity
        binding.root.setOnClickListener{ onMovieClickHandler.invoke(movie) }
    }
}
