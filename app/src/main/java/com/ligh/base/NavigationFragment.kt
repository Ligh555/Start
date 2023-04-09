package com.ligh.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.ligh.R
import com.ligh.base.NavigationViewModel.Companion.POP_UP
import com.ligh.base.NavigationViewModel.Companion.RECYCLE_VIEW
import com.ligh.databinding.FragmentNavigationBinding
import com.ligh.popup.PopUpFragment
import com.ligh.recycleview.RecycleViewFragment

class NavigationFragment : Fragment(), NavigationAdapter.OnItemClick {


    private lateinit var viewModel: NavigationViewModel

    private lateinit var bind : FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind  = FragmentNavigationBinding.inflate(layoutInflater,container,false)
        initView()
        return bind.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
    }

    private fun initView(){
        bind.rvNavigation.apply {
            val linearLayoutManager = LinearLayoutManager(this.context)
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            val navigationAdapter = NavigationAdapter(viewModel.fragmentList)
            navigationAdapter.onItemClick = this@NavigationFragment
            adapter = navigationAdapter
        }
    }

    override fun onClick(position: Int) {
        when(viewModel.fragmentList[position]){
            RECYCLE_VIEW -> {
                parentFragmentManager.commit {
                    add(R.id.fragment_container_view, RecycleViewFragment())
                }
            }
            POP_UP ->{
                parentFragmentManager.commit {
                    add(R.id.fragment_container_view, PopUpFragment())
                }
            }
        }
    }

}