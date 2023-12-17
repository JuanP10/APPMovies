package com.example.desafioandroid.ui.theme.Screen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.data.Movie
import com.example.desafioandroid.data.MoviesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel (private val repository: MoviesRepository): ViewModel() {

    //Extendemos hacia una clase

    private val _Parcelstate = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _Parcelstate

    init {
        viewModelScope.launch {
            _Parcelstate.value = UiState(loading = true)
            repository.requestMovies()

            repository.movies.collect{
                _Parcelstate.value = UiState(movies = it)
            }
        }


    }

    fun onMovieClick(movie: Movie) {
        viewModelScope.launch {
            repository.updateMovie(movie.copy(favorite = !movie.favorite))
        }

    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}