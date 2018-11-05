package co.imdbmovies.data.local.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Movie")
data class Movies @JvmOverloads constructor(
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "overview") var overview: String = "",
        @ColumnInfo(name = "poster_path") var poster_path: String = "",
        @ColumnInfo(name = "release_date") var release_date: String = "",
        @PrimaryKey @ColumnInfo(name = "id") var id: String = ""
) {

    val idForMovie: String
        get() = id

    val titleForMovie: String
        get() = if (title.isNotEmpty()) title else overview

    val overviewForMovie: String
        get() = if (overview.isNotEmpty()) title else ""

    val posterForMovie: String
        get() = poster_path

}