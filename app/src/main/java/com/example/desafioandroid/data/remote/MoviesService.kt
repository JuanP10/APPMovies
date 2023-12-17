package com.example.desafioandroid.data.remote

import retrofit2.http.GET

interface MoviesService {

    @GET("discover/movie?api_key=59f68f115887f65ff61949b100f9a171")
    suspend fun getMovies (): MovieResult

}