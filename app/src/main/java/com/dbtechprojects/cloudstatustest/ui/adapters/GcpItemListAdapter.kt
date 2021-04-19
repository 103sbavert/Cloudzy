package com.dbtechprojects.cloudstatustest.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.databinding.RowItemGcpBinding
import com.dbtechprojects.cloudstatustest.model.GcpItem


class GcpItemListAdapter(private var list: List<GcpItem>) : RecyclerView.Adapter<GcpItemListAdapter.GcpItemViewHolder>() {

    class GcpItemViewHolder(private val binding: RowItemGcpBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GcpItem) {
            //
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
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addevent(event: GcpItem) {
        list += event
    }
}