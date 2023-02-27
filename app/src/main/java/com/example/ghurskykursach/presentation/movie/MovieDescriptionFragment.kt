package com.example.ghurskykursach.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghurskykursach.databinding.FragmentMovieDescriptionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject

//import com.example.ghurskykursach.presentation.movie.youtube.YoutubeActivity

class MovieDescriptionFragment : Fragment(){

    private lateinit var binding: FragmentMovieDescriptionBinding
    private val movieViewModel: MovieDescriptionViewModel by inject()
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        movieViewModel.commentsList.observe(viewLifecycleOwner){ commentList->
            val commentsAdapter = CommentsAdapter(commentList)
            binding.rvComments.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
            binding.rvComments.adapter = commentsAdapter

            binding.btnSendComment.setOnClickListener {
                movieViewModel.sendComment((commentList.size + 1).toString(), movie.id.toString(), binding.etComment.text.toString())
                binding.etComment.text?.clear()
                movieViewModel.processingData(id.toString())
            }
        }
    }
}