package com.ligh.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import com.ligh.databinding.FragmentTableBinding


class TableFragment : Fragment() {

    private lateinit var bind :FragmentTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind  =  FragmentTableBinding.inflate(inflater,container,false)
        initView()
        return  bind.root
    }

    private fun initView(){
       val recyclerView = bind.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this.context, 4)
        recyclerView.adapter = MyAdapter()
    }

}