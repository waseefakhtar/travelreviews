package com.example.travel_reviews.reviews

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_reviews.R
import com.example.travel_reviews.databinding.FragmentReviewsBinding
import com.example.travel_reviews.network.NetworkState
import com.example.travel_reviews.network.ReviewProperty
import com.example.travel_reviews.network.ReviewsSort


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

        initNetworkState(binding)
        initRecyclerView(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initNetworkState(binding: FragmentReviewsBinding) {
        binding.viewModel?.networkState?.observe(viewLifecycleOwner, Observer<NetworkState> { networkState ->
            Log.i("ReviewsDataSource", String.format("initRecyclerView is run: %s", networkState))
        })
    }

    private fun initRecyclerView(binding: FragmentReviewsBinding) {
        val adapter = ReviewsAdapter()
        binding.viewModel?.pagedListLiveData?.observe(viewLifecycleOwner, Observer<PagedList<ReviewProperty>> { pagedList ->
                adapter.submitList(pagedList)
            })
        binding.reviewsRecyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.reviewsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        binding.reviewsRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateSort(
            when (item.itemId) {
                R.id.date_item -> when (viewModel.currentSort) {
                    ReviewsSort.SORT_DATE_ASC -> ReviewsSort.SORT_DATE_DESC
                    else -> ReviewsSort.SORT_DATE_ASC
                }
                R.id.rating_item -> when (viewModel.currentSort) {
                    ReviewsSort.SORT_RATING_ASC -> ReviewsSort.SORT_RATING_DESC
                    else -> ReviewsSort.SORT_RATING_ASC
                }
                else -> null
            }
        )
        return true
    }
}