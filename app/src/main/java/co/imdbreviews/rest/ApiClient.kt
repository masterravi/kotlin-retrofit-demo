package co.imdbreviews.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        val baseURL : String = "https://api.themoviedb.org/3/"
        var retrofit : Retrofit? = null

        val client : Retrofit
            get(){
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }

}
