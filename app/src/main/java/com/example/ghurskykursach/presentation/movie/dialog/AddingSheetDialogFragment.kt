package com.example.ghurskykursach.presentation.movie.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ghurskykursach.databinding.DialogSheetFragmentAddingBinding
import com.example.ghurskykursach.presentation.movie.response.MoviesFirebase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

class AddingSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogSheetFragmentAddingBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSheetFragmentAddingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    suspend fun getStatusMovie(id: String): Boolean {
        var megaStatus = true
        CoroutineScope(Dispatchers.IO).launch {

            database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
                .addOnSuccessListener {
                    it.children.forEach { data ->
                        if (data.key.toString() == id) {
                            megaStatus = false
                        }
                    }
                }

        }
        return megaStatus
    }
}