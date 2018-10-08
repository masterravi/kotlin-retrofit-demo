package co.imdbreviews.rest

import co.imdbreviews.model.MovieDetail
import co.imdbreviews.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/top_rated?")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MovieDetail>

}
