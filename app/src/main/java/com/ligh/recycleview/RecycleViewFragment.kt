package com.ligh.recycleview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
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
            val myRecycleViewAdapter = MyRecycleViewAdapter(viewModel)
            adapter = myRecycleViewAdapter
            val callback = MyItemTouchHelperCallback(myRecycleViewAdapter)
            ItemTouchHelper(callback).attachToRecyclerView(this@apply)
        }
    }

}