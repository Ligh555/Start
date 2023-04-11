package com.ligh.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R

class MyRecycleViewAdapter (private val viewModel: RecycleViewViewModel) : RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>(), MyItemTouchHelperCallback.ItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_recycle_test, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTvContent.text = "我是第${viewModel.getData()[position]}个"
    }

    override fun getItemCount(): Int {
        return viewModel.getData().size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTvContent: TextView = view.findViewById(R.id.tv_content)
    }

    override fun onItemMove(source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
        val fromPosition = source.adapterPosition
        val toPosition = target.adapterPosition
        viewModel.dataSwap(fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDissmiss(source: RecyclerView.ViewHolder) {
        val position = source.adapterPosition
        viewModel.dataRemove(position)
        notifyItemRemoved(position)
    }

    override fun onItemSelect(source: RecyclerView.ViewHolder?) {
        //当拖拽选中时放大选中的view
        source?.itemView?.scaleX = 1.2f
        source?.itemView?.scaleY = 1.2f

    }

    override fun onItemClear(source: RecyclerView.ViewHolder) {
        //当拖拽选结束缩小选中的view
        source.itemView.scaleX = 1.0f
        source.itemView.scaleY = 1.0f
    }
}