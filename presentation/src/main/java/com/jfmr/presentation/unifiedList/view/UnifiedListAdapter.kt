package com.jfmr.presentation.unifiedList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jfmr.presentation.databinding.FragmentItemBinding
import com.jfmr.presentation.unifiedList.model.UnifiedItemList

class UnifiedListAdapter(
    private val listener: (UnifiedItemList) -> Unit,
) : ListAdapter<UnifiedItemList, UnifiedListAdapter.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(private val binding: FragmentItemBinding) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UnifiedItemList?) {
            if (item != null) {
                with(binding) {
                    itemNameValue.text = item.name
                    itemGenreValue.text = item.genre
                    itemYearValue.text = item.year.toString()
                }
                binding.itemImage.load(item.attachmentUI.first().value)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(getItem(position)) }
        return holder.bind(getItem(position))
    }

}

private object DiffUtilCallback : DiffUtil.ItemCallback<UnifiedItemList>() {
    override fun areItemsTheSame(oldItem: UnifiedItemList, newItem: UnifiedItemList) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UnifiedItemList, newItem: UnifiedItemList) =
        oldItem == newItem
}
