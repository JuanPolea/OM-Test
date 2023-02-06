package com.jfmr.presentation.detail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jfmr.presentation.databinding.RecommendationItemListBinding
import com.jfmr.presentation.detail.model.RecommendedItemList
import com.jfmr.presentation.extensions.loadImage

class RecommendationsAdapter(
    private val listener: (RecommendedItemList) -> Unit,
) : ListAdapter<RecommendedItemList, RecommendationsAdapter.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(private val binding: RecommendationItemListBinding) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecommendedItemList?) {
            if (item != null) {
                with(binding) {
                    itemImage.loadImage(item.imageXUI.value)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RecommendationItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(getItem(position)) }
        return holder.bind(getItem(position))
    }

}

private object DiffUtilCallback : DiffUtil.ItemCallback<RecommendedItemList>() {
    override fun areItemsTheSame(oldItem: RecommendedItemList, newItem: RecommendedItemList) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RecommendedItemList, newItem: RecommendedItemList) =
        oldItem == newItem
}
