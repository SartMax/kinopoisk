package com.example.ghurskykursach.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ghurskykursach.databinding.FragmentMovieDescriptionBinding
//import com.example.ghurskykursach.presentation.movie.youtube.YoutubeActivity

class MovieDescriptionFragment : Fragment(){

    private lateinit var binding: FragmentMovieDescriptionBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}