package com.example.ghurskykursach.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghurskykursach.domain.response.Films
import com.example.ghurskykursach.presentation.main.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    val liveData: MutableLiveData<Films> = MutableLiveData()

    fun getResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(mainRepository.getMovie())
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }
}