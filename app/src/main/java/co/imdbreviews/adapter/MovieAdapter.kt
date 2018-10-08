package co.imdbreviews.adapter

import androidx.recyclerview.widget.RecyclerView
import co.imdbreviews.model.MovieDetail
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.imdbreviews.R
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.toast

class MovieAdapter(var  context : Context ,var movieList : List<MovieDetail>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieName.text = "Name : " + movieList.get(position).original_title
        holder.movieId.text = "Id : " + movieList.get(position).id.toString()
        holder.movieDesc.text = "Desc : " + movieList.get(position).overview
        holder.movieDate.text = "Release Date : " + movieList.get(position).release_date

        holder.itemView.setOnClickListener {
            context.toast(movieList.get(position).original_title)
        }
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
        val card_view = itemView.card_view

    }


}