package com.dbtechprojects.cloudzy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudzy.databinding.RowItemAzureBinding
import com.dbtechprojects.cloudzy.model.AzureItem

class AzureItemListAdapter : ListAdapter<AzureItem, AzureItemListAdapter.AzureItemViewHolder>(AzureAdapterDiffUtil()) {

    class AzureItemViewHolder(private val binding: RowItemAzureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AzureItem) {
            binding.title.text = item.title
            binding.description.text = HtmlCompat.fromHtml(
                item.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }.toString(),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            binding.pubDate.text = item.pubDate
            binding.guid.text = item.guid.text
        }

        companion object {
            fun inflateLayout(parent: ViewGroup): AzureItemViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(context)
                    val binding = RowItemAzureBinding.inflate(inflater, this, false)
                    return AzureItemViewHolder(binding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzureItemViewHolder {
        return AzureItemViewHolder.inflateLayout(parent)
    }

    override fun onBindViewHolder(holder: AzureItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AzureAdapterDiffUtil : DiffUtil.ItemCallback<AzureItem>() {

    override fun areItemsTheSame(oldItem: AzureItem, newItem: AzureItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AzureItem, newItem: AzureItem): Boolean {
        return oldItem.guid == newItem.guid
    }
}
