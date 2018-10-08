package co.imdbreviews

import android.app.Application

class IMDBReviewsApp : Application() {

    companion object {
        lateinit var instance: IMDBReviewsApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}