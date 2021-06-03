package com.dbtechprojects.cloudzy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudzy.databinding.RowItemAzureBinding
import com.dbtechprojects.cloudzy.model.AzureItem


class AzureItemListAdapter(private var list: List<AzureItem>) : RecyclerView.Adapter<AzureItemListAdapter.AzureItemViewHolder>() {

    class AzureItemViewHolder(private val binding: RowItemAzureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AzureItem) {
            // TODO: YET TO BE IMPLEMENTED
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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addEvent(event: AzureItem) {
        list += event
    }
}
