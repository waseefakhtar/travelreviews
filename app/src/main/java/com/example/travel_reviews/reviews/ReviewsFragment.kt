package com.example.travel_reviews.reviews

import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_reviews.R
import com.example.travel_reviews.databinding.FragmentReviewsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReviewsFragment : Fragment() {

    private lateinit var binding: FragmentReviewsBinding
    private lateinit var viewModel: ReviewsViewModel
    private val adapter = ReviewsAdapter()

    private var reviewsJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(ReviewsViewModel::class.java)

        val dividerItemDecoration = DividerItemDecoration(
            binding.reviewsRecyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        binding.reviewsRecyclerView.addItemDecoration(dividerItemDecoration)

        initAdapter()
        initNetworkState(binding)
        initReviewList(binding)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun initAdapter() {
        binding.reviewsRecyclerView.adapter = adapter
    }

    private fun initNetworkState(binding: FragmentReviewsBinding) {
        /*binding.viewModel?.networkState?.observe(this, Observer<NetworkState> { networkState ->
            Log.i("ReviewsDataSource", String.format("initRecyclerView is run: %s", networkState))
        })*/
    }

    private fun initReviewList(binding: FragmentReviewsBinding) {
        // Make sure we cancel the previous job before creating a new one
        reviewsJob?.cancel()
        reviewsJob = lifecycleScope.launch {
            adapter.submitData(binding.viewModel?.loadData()?.value!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
    }*/
}