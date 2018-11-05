package co.imdbreviews.model

import co.imdbmovies.data.local.movies.Movies

data class MovieResponse(var page: Int?, var total_results: Int?, var total_pages: Int?, var results: List<Movies>?)
