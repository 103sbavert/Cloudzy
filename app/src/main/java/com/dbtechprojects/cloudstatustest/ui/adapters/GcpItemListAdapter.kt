package com.dbtechprojects.cloudstatustest.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.RowItemGcpBinding
import com.dbtechprojects.cloudstatustest.model.GcpItem

class GcpItemListAdapter : ListAdapter<GcpItem, GcpItemListAdapter.GcpItemViewHolder>(GcpAdapterDiffUtil()) {

    class GcpItemViewHolder(private val binding: RowItemGcpBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateLayout(parent: ViewGroup): GcpItemViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = RowItemGcpBinding.inflate(inflater, parent, false)
                    return GcpItemViewHolder(binding)
                }
            }
        }

        fun bind(item: GcpItem) {
            setSeverityText(item)
            binding.externalDesc.text = item.externalDesc
            binding.statusImpact.text = binding.root.context.getString(R.string.status_impact_text, item.statusImpact)
            binding.serviceKey.text = binding.root.context.getString(R.string.service_key_text, item.serviceKey)
            binding.serviceName.text = binding.root.context.getString(R.string.service_name_text, item.serviceName)
            binding.created.text = item.created
            binding.id.text = binding.root.context.getString(R.string.id_text, item.id)
        }

        private fun setSeverityText(item: GcpItem) {
            val severityLevel = item.severity?.replaceFirstChar { it -> it.uppercase() }

            // set the color int based on the severity level
            val colorInt: Int = when (severityLevel) {
                "High" -> ContextCompat.getColor(binding.root.context, R.color.saffron)
                "Medium" -> ContextCompat.getColor(binding.root.context, R.color.yellow)
                "Low" -> ContextCompat.getColor(binding.root.context, R.color.green)
                else -> ContextCompat.getColor(binding.root.context, R.color.black)
            }

            // convert the color to a hex code and get rid of the first to characters (because they represent the alpha)
            val color = "#${Integer.toHexString(colorInt).substring(2)}"
            Log.e("TAG", "setSeverityText: $color")

            // use HTML Compat to convert plain text into formatted text
            val text = HtmlCompat.fromHtml(
                binding.root.context.getString(R.string.severity_text, severityLevel, color),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            binding.severity.text = text
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
