package com.ligh.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R

class NavigationAdapter(val datas : List<String>) : RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {

    lateinit var  onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_navigation,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.apply {
            text = datas[position]
            setOnClickListener{
                onItemClick.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.rv_navigation_item_text)
    }

    interface OnItemClick{
        fun onClick(position : Int)
    }
}