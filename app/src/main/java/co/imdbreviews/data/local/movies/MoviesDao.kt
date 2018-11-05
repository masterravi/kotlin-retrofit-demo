package co.imdbmovies.data.local.movies

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.imdbreviews.model.MovieDetail


@Dao
interface MoviesDao {

    @get:Query("SELECT * FROM movie")
    val all: List<Movies>

    @Query("SELECT * FROM movie where id = :id")
    fun findMovieById(id: String): Movies

    @Query("SELECT * FROM movie where title = :title")
    fun findMovieByTitle(title: String): Movies

    @Query("SELECT COUNT(*) from movie")
    fun countMovies(): Int

    @Insert
    fun insertAll(movies: List<Movies>)

    @Delete
    fun delete(movie: List<Movies>)
}