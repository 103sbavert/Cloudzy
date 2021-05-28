package com.dbtechprojects.cloudstatustest.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.databinding.RowItemGcpBinding
import com.dbtechprojects.cloudstatustest.model.GcpItem

class GcpItemListAdapter : ListAdapter<GcpItem, GcpItemListAdapter.GcpItemViewHolder>(GcpAdapterDiffUtil()) {

    class GcpItemViewHolder(private val binding: RowItemGcpBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GcpItem) {
            binding.title.text = item.externalDesc
            binding.description.text = item.mostRecentUpdate?.text ?: ""
            binding.severity.text = item.severity
            binding.pubDate.text = item.created
            binding.statusImpact.text = item.statusImpact
            binding.status.text = "Status: ${item.mostRecentUpdate?.status}"
        }

        companion object {
            fun inflateLayout(parent: ViewGroup): GcpItemViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = RowItemGcpBinding.inflate(inflater, parent, false)
                    return GcpItemViewHolder(binding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GcpItemViewHolder {
        return GcpItemViewHolder.inflateLayout(parent)
    }

    override fun onBindViewHolder(holder: GcpItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GcpAdapterDiffUtil : DiffUtil.ItemCallback<GcpItem>() {

    override fun areItemsTheSame(oldItem: GcpItem, newItem: GcpItem): Boolean {
        return oldItem.externalDesc == newItem.externalDesc
    }

    override fun areContentsTheSame(oldItem: GcpItem, newItem: GcpItem): Boolean {
        return oldItem.id == newItem.id
    }
}
