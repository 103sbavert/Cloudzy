package com.dbtechprojects.cloudzy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.RowItemGcpBinding
import com.dbtechprojects.cloudzy.model.AffectedProduct
import com.dbtechprojects.cloudzy.model.GcpItem

class GcpItemListAdapter(private val onButtonClickListener: OnButtonsClickListener) :
    ListAdapter<GcpItem, GcpItemListAdapter.GcpItemViewHolder>(GcpAdapterDiffUtil()) {

    class GcpItemViewHolder(private val binding: RowItemGcpBinding, private val onButtonClickListener: OnButtonsClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateLayout(parent: ViewGroup, onButtonClickListener: OnButtonsClickListener): GcpItemViewHolder {
                parent.apply {
                    val inflater = LayoutInflater.from(context)
                    val binding = RowItemGcpBinding.inflate(inflater, this, false)
                    return GcpItemViewHolder(binding, onButtonClickListener)
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
            item.affectedProducts?.let { setAffectedProductsText(it) }
            binding.updatesButton.setOnClickListener { onButtonClickListener.onUpdatesButtonClickListener(item.id) }
        }

        private fun setAffectedProductsText(list: List<AffectedProduct>) {
            val text = StringBuilder("Affected Products: ")
            for (each in list.withIndex()) {
                val affectedProduct = each.value.title
                text.append(affectedProduct)
                if (each.index != list.lastIndex) text.append(", ")
            }
            binding.affectedProducts.text = text.toString()
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

            // use HTML Compat to convert plain text into formatted text
            val text = HtmlCompat.fromHtml(
                binding.root.context.getString(R.string.severity_text, severityLevel, color),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            binding.severity.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GcpItemViewHolder {
        return GcpItemViewHolder.inflateLayout(parent, onButtonClickListener)
    }

    override fun onBindViewHolder(holder: GcpItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnButtonsClickListener {
        fun onUpdatesButtonClickListener(id: String)
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
