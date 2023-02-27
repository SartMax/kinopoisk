package com.example.ghurskykursach.presentation.movie

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ghurskykursach.R
import com.example.ghurskykursach.databinding.FragmentMovieDescriptionBinding
import com.example.ghurskykursach.presentation.movie.response.MoviesFirebase
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
        var id = arguments?.getInt("ID")

        movieViewModel.processingData(id.toString())

        id?.let { movieViewModel.getResponse(it) }

        movieViewModel.liveData.observe(viewLifecycleOwner) { movie ->

            Log.e("MovieTag", movie.toString())
            try{
                Glide.with(binding.root)
                    .load(movie.backdrop?.url)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivBackPoster)
            }catch (e: Exception){
                Log.e("Error", e.message.toString() )
            }

            try{
                Glide.with(binding.root)
                    .load(movie.poster?.url)
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivPoster)
            }catch (e: Exception){
                Log.e("Error", e.message.toString() )
            }

            binding.btnBack.setOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
            binding.btnMore.setOnClickListener {
                val bundle = Bundle()
                if (movie.backdrop?.url == null){
                    bundle.putSerializable("MOVIE", MoviesFirebase(movie.id.toString(), movie.name!!, movie.poster?.url!!, ""))
                }else
                    bundle.putSerializable("MOVIE", MoviesFirebase(movie.id.toString(), movie.name!!, movie.poster?.url!!, movie.backdrop.url))

                Navigation.findNavController(binding.root).navigate(R.id.action_movieDescriptionFragment_to_addingSheetDialogFragment, bundle)
            }
            binding.tvMovieName.text = movie.name
            binding.tvBudget.text = movie.budget?.value.toString()
            binding.tvBudgetWorld.text = movie.fees?.world?.value.toString()


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
}