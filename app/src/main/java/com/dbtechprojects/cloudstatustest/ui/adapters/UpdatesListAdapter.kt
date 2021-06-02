package com.dbtechprojects.cloudstatustest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.databinding.RowItemUpdateBinding
import com.dbtechprojects.cloudstatustest.model.Update

class UpdatesListAdapter(private val dataList: List<Update>) : RecyclerView.Adapter<UpdatesListAdapter.UpdatesViewHolder>() {

    class UpdatesViewHolder(private val binding: RowItemUpdateBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateLayout(parent: ViewGroup): UpdatesViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(context)
                    val binding = RowItemUpdateBinding.inflate(inflater, this, false)
                    return UpdatesViewHolder(binding)
                }
            }
        }

        fun bind(item: Update) {
            binding.text.text = item.text
            binding.status.text = item.status
            binding.created.text = item.created
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdatesViewHolder {
        return UpdatesViewHolder.inflateLayout(parent)
    }

    override fun onBindViewHolder(holder: UpdatesViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}
