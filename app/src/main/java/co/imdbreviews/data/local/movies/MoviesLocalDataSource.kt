package co.imdbmovies.data.local.movies

import android.content.Context
import androidx.annotation.VisibleForTesting
import co.imdbmovies.data.MoviesDataSource
import com.example.android.architecture.blueprints.todoapp.util.AppExecutors


/**
 * Concrete implementation of a data source as a db.
 */
class MoviesLocalDataSource private constructor(
        val appExecutors: AppExecutors,
        val moviesDao: MoviesDao
) : MoviesDataSource {
    override fun getMovies(mContext: Context, callback: MoviesDataSource.LoadMoviesCallback){
        appExecutors.diskIO.execute {
            val movies = moviesDao.all
            appExecutors.mainThread.execute {
                if (movies.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable()
                } else {
                    callback.onMoviesLoaded(movies)
                }
            }
        }
    }



    override fun saveMovies(movies : List<Movies>) {
        appExecutors.diskIO.execute { moviesDao.insertAll(movies) }
    }


    override fun refreshMovies() {
        // Not required because the {@link MoviesRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }


    override fun getMovieDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private var INSTANCE: MoviesLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors,moviesDao: MoviesDao): MoviesLocalDataSource {
            if (INSTANCE == null) {
                synchronized(MoviesLocalDataSource::javaClass) {
                    INSTANCE = MoviesLocalDataSource(appExecutors,moviesDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}