package com.test.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ligh.R

class TestAdapter(val context: Context) : Adapter<TestAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_test_commom_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.indexView?.text = "$position"
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val indexView: TextView? = itemView.findViewById(R.id.tv_index)
    }
}


