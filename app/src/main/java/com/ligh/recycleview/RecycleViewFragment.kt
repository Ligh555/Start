package com.ligh.recycleview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ligh.databinding.FragmentRecycleViewBinding
import kotlin.math.abs
import kotlin.math.min

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

            val callback = MyItemTouchHelperCallback(object : MyItemTouchHelperCallback.ItemTouchHelperListener{
                override fun onItemMove(source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
                    val fromPosition = source.adapterPosition
                    val toPosition = target.adapterPosition
                    viewModel.dataSwap(fromPosition,toPosition)
                    this@apply.adapter?.notifyItemMoved(fromPosition,toPosition)
                    this@apply.adapter?.notifyItemRangeChanged(min(fromPosition, toPosition), abs(fromPosition - toPosition) +1)
                }

                override fun onItemDissmiss(source: RecyclerView.ViewHolder) {
                    val position = source.adapterPosition
                    viewModel.dataRemove(position)
                    this@apply.adapter?.notifyItemRemoved(position)
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
            })
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(this)

            val adapterListener = object : MyRecycleViewAdapter.IItemClickListener{
                override fun onItemTouch(viewHolder: MyRecycleViewAdapter.MyViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }

            }
            val myRecycleViewAdapter = MyRecycleViewAdapter(viewModel,adapterListener)
            adapter = myRecycleViewAdapter
        }
    }

}