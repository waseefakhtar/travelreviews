package com.example.travel_reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_reviews.databinding.ListViewItemBinding
import com.example.travel_reviews.network.ReviewProperty

class ReviewsAdapter() : ListAdapter<ReviewProperty,
        ReviewsAdapter.ReviewPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ReviewPropertyViewHolder {
        return ReviewPropertyViewHolder(ListViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ReviewPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ReviewProperty>() {
        override fun areItemsTheSame(oldItem: ReviewProperty, newItem: ReviewProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ReviewProperty, newItem: ReviewProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ReviewPropertyViewHolder(private var binding:
                                   ListViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(reviewProperty: ReviewProperty) {
            binding.property = reviewProperty
            binding.executePendingBindings()
        }
    }
}