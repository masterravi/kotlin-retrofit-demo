package co.imdbmovies.data

import android.content.Context
import co.imdbmovies.data.local.movies.Movies

interface MoviesDataSource {
    interface LoadMoviesCallback {

        fun onMoviesLoaded(tasks: List<Movies>)

        fun onDataNotAvailable()
    }

    interface GetMovieCallback {

        fun onTaskLoaded(movie: Movies)

        fun onDataNotAvailable()
    }


    fun getMovies(mContext : Context,callback: LoadMoviesCallback)

    fun saveMovies(movies : List<Movies>)

    fun getMovieDetail()

    fun refreshMovies()

}