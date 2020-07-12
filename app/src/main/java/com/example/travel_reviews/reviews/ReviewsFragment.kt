package com.example.travel_reviews.reviews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_reviews.databinding.FragmentReviewsBinding
import com.example.travel_reviews.network.ReviewProperty


class ReviewsFragment : Fragment() {

    private val viewModel: ReviewsViewModel by lazy {
        ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReviewsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initRecyclerView(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initRecyclerView(binding: FragmentReviewsBinding) {
        val adapter = ReviewsAdapter()
        binding.viewModel?.pagedListLiveData?.observe(this, Observer<PagedList<ReviewProperty>> { pagedList ->
            Log.i("ReviewsDataSource", String.format("1 initRecyclerView is run: %s", pagedList.size))

                adapter.submitList(pagedList)
            })
        binding.reviewsRecyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.reviewsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        binding.reviewsRecyclerView.addItemDecoration(dividerItemDecoration)
    }
}