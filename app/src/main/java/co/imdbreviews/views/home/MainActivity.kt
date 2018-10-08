package co.imdbreviews.views.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.imdbreviews.R
import co.imdbreviews.adapter.MovieAdapter
import co.imdbreviews.model.MovieDetail
import co.imdbreviews.model.MovieResponse
import co.imdbreviews.rest.ApiClient
import co.imdbreviews.rest.ApiInterface
import co.imdbreviews.utils.ListDecorations
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var recyclerView: RecyclerView
    val TAG: String = MainActivity::class.java.simpleName
    val API_KEY: String = "ec44357d71b936de6ee7f5aa7837b6a4"
    lateinit var myMovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListDecorations(20))
        recyclerView.setHasFixedSize(true)

    }

    fun getIMDBReviews(){
        if (API_KEY.isEmpty()) {
            toast("Please obtain your API KEY first from www.themoviedb.org")
            return
        }

        progressBar.visibility = View.VISIBLE

        var apiServices = ApiClient.client.create(ApiInterface::class.java)

        val call = apiServices.getTopRatedMovies(API_KEY)

       call.enqueue(object : Callback<MovieResponse> {
           override fun onResponse(call: Call<MovieResponse>?, response: retrofit2.Response<MovieResponse>?) {
               var listOfMovies: List<MovieDetail> = response?.body()?.results!!
               myMovieAdapter = MovieAdapter(applicationContext, listOfMovies)
               recyclerView.setAdapter(myMovieAdapter)
               progressBar.visibility = View.GONE
           }

           override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
               progressBar.visibility = View.GONE
               Log.e(TAG, t.toString())
           }
       })


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
