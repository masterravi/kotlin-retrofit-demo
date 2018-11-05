package co.imdbreviews.views.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.imdbmovies.data.MoviesDataSource
import co.imdbmovies.data.MoviesRepository
import co.imdbmovies.data.local.OMDBDatabase
import co.imdbmovies.data.local.movies.Movies
import co.imdbmovies.data.local.movies.MoviesLocalDataSource
import co.imdbmovies.data.remote.MoviesRemoteDataSource
import co.imdbreviews.R
import co.imdbreviews.adapter.MovieAdapter
import co.imdbreviews.utils.ListDecorations
import com.example.android.architecture.blueprints.todoapp.util.AppExecutors
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var recyclerView: RecyclerView
    val TAG: String = MainActivity::class.java.simpleName
    val API_KEY: String = "977483df3d95a5ab7178f18ce2bdb5d1"
    lateinit var myMovieAdapter: MovieAdapter
    lateinit var listOfMovies: List<Movies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        listOfMovies= listOf<Movies>()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.addItemDecoration(ListDecorations(20))
        recyclerView.setHasFixedSize(true)
        getIMDBReviews()

    }

    fun getIMDBReviews() {
        if (API_KEY.isEmpty()) {
            toast("Please obtain your API KEY first from www.themoviedb.org")
            return
        }

        progressBar.visibility = View.VISIBLE

        val database = OMDBDatabase.getInstance(this@MainActivity)
        val reviewsRepository = MoviesRepository.getInstance(MoviesRemoteDataSource,
                MoviesLocalDataSource.getInstance(AppExecutors(),database.moviesDao()))

        reviewsRepository.getMovies(this@MainActivity,object : MoviesDataSource.LoadMoviesCallback{
            override fun onMoviesLoaded(listOfMovies: List<Movies>) {
                myMovieAdapter = MovieAdapter(applicationContext, listOfMovies)
                recyclerView.setAdapter(myMovieAdapter)
                progressBar.visibility = View.GONE
            }

            override fun onDataNotAvailable() {
                Snackbar.make(
                        recyclerView, // Parent view
                        "No Movies Available", // Message to show
                        Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                progressBar.visibility = View.GONE
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
