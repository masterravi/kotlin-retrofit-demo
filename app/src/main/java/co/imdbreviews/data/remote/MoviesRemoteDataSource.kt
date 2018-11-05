package co.imdbmovies.data.remote

import android.content.Context
import android.os.Handler
import android.util.Log
import co.imdbmovies.data.MoviesDataSource
import co.imdbmovies.data.local.movies.Movies
import co.imdbreviews.model.MovieResponse
import co.imdbreviews.rest.ApiClient
import co.imdbreviews.rest.ApiInterface
import co.imdbreviews.utils.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback

object MoviesRemoteDataSource : MoviesDataSource {
    private const val SERVICE_LATENCY_IN_MILLIS = 5000L

    override fun getMovies(mContext: Context, callback: MoviesDataSource.LoadMoviesCallback){
        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        var listOfMovies : List<Movies> =listOf<Movies>()
        val call = apiServices.getTopRatedMovies(API_KEY)
        Log.e("CHECK_TOTAL", listOfMovies.toString())


        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: retrofit2.Response<MovieResponse>?) {
                listOfMovies = response?.body()?.results!!
                Log.e("TOTAL", listOfMovies.toString())
                Handler().postDelayed({
                    callback.onMoviesLoaded(listOfMovies)
                }, SERVICE_LATENCY_IN_MILLIS)
            }


            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.e("TOTAL", t.toString())
                Handler().postDelayed({
                    callback.onDataNotAvailable()
                }, SERVICE_LATENCY_IN_MILLIS)
            }
        })
    }


    override fun saveMovies(movies: List<Movies>) {

    }

    override fun getMovieDetail() {
    }

    override fun refreshMovies() {
    }

}