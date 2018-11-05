package co.imdbmovies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.imdbmovies.data.local.movies.Movies
import co.imdbmovies.data.local.movies.MoviesDao

@Database(entities = arrayOf(Movies::class),version = 1)
abstract  class OMDBDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        private var INSTANCE: OMDBDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): OMDBDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            OMDBDatabase::class.java, "omdb.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}