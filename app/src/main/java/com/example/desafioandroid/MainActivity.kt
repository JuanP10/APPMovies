package com.example.desafioandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.desafioandroid.data.MoviesRepository
import com.example.desafioandroid.data.local.LocalDataSource
import com.example.desafioandroid.data.local.MoviesDataBase
import com.example.desafioandroid.data.remote.RemoteDataSource
import com.example.desafioandroid.ui.theme.Screen.Home

//Api Key: 59f68f115887f65ff61949b100f9a171

//MVVM //Sacar el código de nuestra vista (Model View ViewModel)
class MainActivity : ComponentActivity() {

    private lateinit var db : MoviesDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            MoviesDataBase::class.java, "movies-DB"
        ).build()

        val repository = MoviesRepository(
            localDataSource = LocalDataSource((db.moviesDao())),
            remoteDataSource = RemoteDataSource()
        )


        setContent {
            Home(repository)
        }
    }
}
