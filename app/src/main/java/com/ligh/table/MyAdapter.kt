package com.ligh.table

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R


internal class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 判断是否是第一列
        if (position % 4 == 0) {
            holder.tvContent.text = "第" + (position / 4 + 1) + "行"
            holder.tvContent.setBackgroundColor(Color.parseColor("#EEEEEE"))
        } else {
            holder.tvContent.text = "数据$position"
            holder.tvContent.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        // 判断是否是表头
        if (position < 4) {
            holder.tvContent.text = "表头$position"
            holder.tvContent.setBackgroundColor(Color.parseColor("#CCCCCC"))
        }
    }

    override fun getItemCount(): Int {
        return 100 // 假设有 100 条数据
    }
}

internal class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvContent: TextView

    init {
        tvContent = itemView.findViewById(R.id.tv_content)
    }
}
