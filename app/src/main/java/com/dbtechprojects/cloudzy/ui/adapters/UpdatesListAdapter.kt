package com.dbtechprojects.cloudzy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudzy.databinding.RowItemUpdateBinding
import com.dbtechprojects.cloudzy.model.Update
import io.noties.markwon.Markwon

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
            setItemText(item.text)

            // converts ABCDEFG to abcdefg and then Abcdefg
            binding.status.text = item.status.lowercase().replaceFirstChar { it.uppercase() }
            binding.created.text = item.created
        }

        private fun setItemText(text: String) {
            val newText = text.replace(Regex("\n"), "\n\n")
            Markwon.create(binding.root.context).setMarkdown(binding.text, newText)
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
