package com.example.travel_reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_reviews.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment() {

    private val viewModel: ReviewsViewModel by lazy {
        ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReviewsBinding.inflate(inflater)

        initRecyclerView(binding)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initRecyclerView(binding: FragmentReviewsBinding) {
        binding.reviewsRecyclerView.adapter = ReviewsAdapter()

        val dividerItemDecoration = DividerItemDecoration(
            binding.reviewsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        binding.reviewsRecyclerView.addItemDecoration(dividerItemDecoration)
    }
}