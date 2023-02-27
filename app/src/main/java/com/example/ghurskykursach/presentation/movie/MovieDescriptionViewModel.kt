package com.example.ghurskykursach.presentation.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghurskykursach.domain.response_by_id.DocsById
import com.example.ghurskykursach.presentation.movie.repository.MovieRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDescriptionViewModel(private val movieRepository: MovieRepository): ViewModel() {

}