package com.dbtechprojects.cloudstatustest.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.model.AwsItem



class AwsItemListAdapter(
    private var list: List<AwsItem>,

    )
    : RecyclerView.Adapter<AwsItemViewHolder>() {

    fun addevent(event: AwsItem){
        list += event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwsItemViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return AwsItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: AwsItemViewHolder, position: Int) {
        Log.d("rv", "binding")

        val itemView = holder.itemView
        val awsitem = list[position]

        Log.d("rv", awsitem.description)

        val rowTitle = itemView.findViewById<TextView>(R.id.row_Aws_Title)
        val rowDesc = itemView.findViewById<TextView>(R.id.row_Aws_Desc)
        val rowDate = itemView.findViewById<TextView>(R.id.row_Aws_Date)

        rowTitle.setText(awsitem.title)
        rowDesc.setText(awsitem.description)
        rowDate.setText(awsitem.pubDate)



    }

    override fun getItemCount(): Int = list.size

}


class AwsItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item_aws, parent, false)) {

    fun bind(item: AwsItem) {}

}