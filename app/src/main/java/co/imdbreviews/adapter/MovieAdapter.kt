package co.imdbreviews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.imdbmovies.data.local.movies.Movies
import co.imdbreviews.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(var  context : Context ,var movieList : List<Movies>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieId.text = "Id : " + movieList.get(position).id.toString()
        holder.movieDesc.text = "Desc : " + movieList.get(position).overview
        holder.movieDate.text = "Release Date : " + movieList.get(position).release_date

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName = itemView.movieName
        val movieId = itemView.movieId
        val movieDesc = itemView.movieDesc
        val movieDate = itemView.movieDate

    }


}