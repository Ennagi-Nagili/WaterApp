package com.annaginagili.waterapp.fragment.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annaginagili.waterapp.adapter.NewsAdapter
import com.annaginagili.waterapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val newsRecyclerView = binding.newsRecyclerView

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsAdapter = NewsAdapter(mutableListOf())
        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.newsData.observe(viewLifecycleOwner) { articles ->
            newsAdapter.addData(articles)
        }
        viewModel.getNewsData(requireContext())

        return binding.root
    }
}