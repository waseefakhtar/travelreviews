package com.example.travel_reviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
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

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }
}