package com.dbtechprojects.cloudstatustest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.databinding.RowItemAwsBinding
import com.dbtechprojects.cloudstatustest.model.AwsItem


class AwsItemListAdapter(private var list: List<AwsItem>) : RecyclerView.Adapter<AwsItemListAdapter.AwsItemViewHolder>() {

    class AwsItemViewHolder(private val binding: RowItemAwsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AwsItem) {
            binding.rowAwsTitle.text = item.title
            binding.rowAwsDesc.text = item.description
            binding.rowAwsDate.text = item.pubDate
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

    fun addevent(event: AwsItem) {
        list += event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwsItemViewHolder {
        return AwsItemViewHolder.inflateLayout(parent)
    }

    override fun onBindViewHolder(holder: AwsItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
