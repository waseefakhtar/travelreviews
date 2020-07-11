package com.example.travel_reviews

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_reviews.network.ReviewProperty

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<ReviewProperty>?) {
    val adapter = recyclerView.adapter as ReviewsAdapter
    adapter.submitList(data)
}

/*@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}*/