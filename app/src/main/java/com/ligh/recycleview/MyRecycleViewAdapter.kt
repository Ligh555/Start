package com.ligh.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R
import kotlin.math.abs
import kotlin.math.min

class MyRecycleViewAdapter (private val viewModel: RecycleViewViewModel,private val listener: IItemClickListener) : RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_recycle_test, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTvContent.text = "我是第${viewModel.getData()[position]}个"
        holder.mController.setOnTouchListener(){_,_->
            listener.onItemTouch(holder)
            true
        }
    }

    override fun getItemCount(): Int {
        return viewModel.getData().size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTvContent: TextView = view.findViewById(R.id.tv_content)
        val mController:View = view.findViewById(R.id.view_controller)
    }

    interface IItemClickListener{
        fun onItemTouch(viewHolder: MyViewHolder)
    }
}