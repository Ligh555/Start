package com.ligh.recycleview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R
import com.ligh.databinding.FragmentRecycleViewBinding

class RecycleViewFragment : Fragment() {

    private val viewModel by viewModels<RecycleViewViewModel>()

    private lateinit var bind: FragmentRecycleViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRecycleViewBinding.inflate(inflater, container, false)

        initView()

        return bind.root
    }

    private fun initView() {
        bind.recycleView.apply {
            layoutManager = LinearLayoutManager(this@RecycleViewFragment.context)
            adapter = MyRecycleViewAdapter()
        }
    }

    class MyRecycleViewAdapter : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_recycle_test, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.mTvContent.text = "我是第${position}个"
        }

        override fun getItemCount(): Int {
            return 10
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTvContent: TextView = view.findViewById(R.id.tv_content)
    }
}