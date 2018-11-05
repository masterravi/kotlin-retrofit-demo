package co.imdbmovies.data

import android.content.Context
import android.util.Log
import co.imdbmovies.data.local.movies.Movies
import co.imdbreviews.utils.NetWorkConection
import java.util.ArrayList

class MoviesRepository(
        val moviesLocalDataSource: MoviesDataSource,
        val moviesRemoteDataSource: MoviesDataSource
):MoviesDataSource {

    override fun saveMovies(movies : List<Movies>) {
        moviesLocalDataSource.saveMovies(movies)
    }

    override fun getMovies(context: Context,callback: MoviesDataSource.LoadMoviesCallback) {
        if(!NetWorkConection.isNEtworkConnected(context)){
            Log.e("REMOTE CALLED", "Hello".toString())

            moviesRemoteDataSource.getMovies(context,object : MoviesDataSource.LoadMoviesCallback{
                override fun onMoviesLoaded(movies: List<Movies>) {
                    Log.e("REMOTE DATA", "Hello".toString())
                    callback.onMoviesLoaded(movies)
                }

                override fun onDataNotAvailable() {
                    Log.e("REMOTE NO DATA", "Hello".toString())
                    callback.onDataNotAvailable()

                }
            })
        }else{
            moviesLocalDataSource.getMovies(context,object : MoviesDataSource.LoadMoviesCallback{
                override fun onMoviesLoaded(movies: List<Movies>) {
                    callback.onMoviesLoaded(movies)
                }

                override fun onDataNotAvailable() {
                    getTasksFromRemoteDataSource(context,callback)
                }
            })

        }


    }

    private fun getTasksFromRemoteDataSource(context:Context,callback: MoviesDataSource.LoadMoviesCallback) {
        moviesRemoteDataSource.getMovies(context,object : MoviesDataSource.LoadMoviesCallback{
            override fun onMoviesLoaded(movies: List<Movies>) {
                Log.e("REMOTE DATA", "Hello".toString())
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                Log.e("REMOTE NO DATA", "Hello".toString())
                callback.onDataNotAvailable()

            }
        })


    }

    override fun getMovieDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private var INSTANCE: MoviesRepository? = null

        @JvmStatic fun getInstance(
                moviesLocalDataSource: MoviesDataSource,
                moviesRemoteDataSource: MoviesDataSource): MoviesRepository {
            return INSTANCE ?: MoviesRepository(moviesLocalDataSource, moviesRemoteDataSource)
                    .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }


}