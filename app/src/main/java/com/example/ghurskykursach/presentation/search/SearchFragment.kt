package com.example.ghurskykursach.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghurskykursach.databinding.FragmentMainBinding
import com.example.ghurskykursach.databinding.FragmentSearchBinding
import com.example.ghurskykursach.domain.response.Docs
import com.example.ghurskykursach.presentation.main.MainAdapter
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(){

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by inject()
    private var responseBody: MutableList<Docs> = emptyList<Docs>().toMutableList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}