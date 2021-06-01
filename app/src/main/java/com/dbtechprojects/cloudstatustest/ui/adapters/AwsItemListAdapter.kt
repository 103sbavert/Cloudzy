package com.dbtechprojects.cloudstatustest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.databinding.RowItemAwsBinding
import com.dbtechprojects.cloudstatustest.model.AwsItem

class AwsItemListAdapter : ListAdapter<AwsItem, AwsItemListAdapter.AwsItemViewHolder>(AwsAdapterDiffUtil()) {

    class AwsItemViewHolder(private val binding: RowItemAwsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AwsItem) {
            binding.title.text = item.title
            binding.description.text = item.description
            binding.pubDate.text = item.pubDate
            binding.guid.text = item.guid.text
        }

        companion object {
            fun inflateLayout(parent: ViewGroup): AwsItemViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = RowItemAwsBinding.inflate(inflater, parent, false)
                    return AwsItemViewHolder(binding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwsItemViewHolder {
        return AwsItemViewHolder.inflateLayout(parent)
    }

    override fun onBindViewHolder(holder: AwsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class AwsAdapterDiffUtil : DiffUtil.ItemCallback<AwsItem>() {

    override fun areItemsTheSame(oldItem: AwsItem, newItem: AwsItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AwsItem, newItem: AwsItem): Boolean {
        return oldItem.guid == newItem.guid
    }
}
