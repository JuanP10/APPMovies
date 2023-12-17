package com.example.desafioandroid.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.desafioandroid.data.Movie
import kotlinx.coroutines.flow.Flow

@Database(entities = [LocalMovie::class], version = 1)
abstract class MoviesDataBase: RoomDatabase (){
    abstract fun moviesDao(): MoviesDao   //Cómo acccedemos a la base

}

@Dao
interface  MoviesDao{
    @Query("SELECT * FROM LocalMovie")
    fun getMovies(): Flow<List<LocalMovie>>

    @Update
    suspend fun updateMovie (movie:LocalMovie)

    @Query ("SELECT COUNT(*) FROM LocalMovie")
    suspend fun conteo(): Int

    @Insert
    suspend fun insertAll(movies: List<LocalMovie>)
}


@Entity
data class LocalMovie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean = false
)



fun LocalMovie.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    favorite = favorite
)


fun Movie.toLocalMovie() = LocalMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    favorite = favorite
)

