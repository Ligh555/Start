package com.ligh.recycleview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MyItemTouchHelperCallback(private val mListener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //val dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //允许上下的拖动
        //val dragFlags =ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; //允许左右的拖动
        //val swipeFlags = ItemTouchHelper.LEFT; //只允许从右向左侧滑
        //val swipeFlags = ItemTouchHelper.DOWN; //只允许从上向下侧滑
        //一般使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值
        //makeMovementFlags(dragFlags, swipeFlags)
        val dragFlags =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT //允许上下左右的拖动
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //通过接口传递拖拽交换数据的起始位置和目标位置的ViewHolder
        mListener.onItemMove(viewHolder, target)
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false //长按不启用拖拽，默认启用
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false //不启用拖拽删除
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //移动删除回调,如果不用可以不用理
        // mAdapter.onItemDissmiss(viewHolder);
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            //当滑动或者拖拽view的时候通过接口返回该ViewHolder
            mListener.onItemSelect(viewHolder)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (!recyclerView.isComputingLayout) {
            //当需要清除之前在onSelectedChanged或者onChildDraw,onChildDrawOver设置的状态或者动画时通过接口返回该ViewHolder
            mListener.onItemClear(viewHolder)
        }
    }

    interface ItemTouchHelperListener{
        //数据交换
        fun onItemMove( source: RecyclerView.ViewHolder , target: RecyclerView.ViewHolder)

        //数据删除
        fun onItemDissmiss(source :RecyclerView.ViewHolder)

        //选中
        fun onItemSelect(source : RecyclerView.ViewHolder?)

        //删除
        fun onItemClear(source : RecyclerView.ViewHolder)
    }
}