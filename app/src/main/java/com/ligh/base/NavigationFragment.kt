package com.ligh.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.ligh.R
import com.ligh.animation.AnimationFragment
import com.ligh.base.NavigationViewModel.Companion.ANIMATION
import com.ligh.base.NavigationViewModel.Companion.BOTTOM_POPOVER
import com.ligh.base.NavigationViewModel.Companion.PERMISSION
import com.ligh.base.NavigationViewModel.Companion.POP_UP
import com.ligh.base.NavigationViewModel.Companion.RECYCLE_VIEW
import com.ligh.base.NavigationViewModel.Companion.TABLE
import com.ligh.databinding.FragmentNavigationBinding
import com.ligh.permission.PermissionFragment
import com.ligh.popup.PopUpFragment
import com.ligh.recycleview.RecycleViewFragment
import com.ligh.table.TableFragment
import com.ligh.widget.BottomPopoverFragment

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
                gotoFragment(RecycleViewFragment())
            }
            POP_UP ->{
                gotoFragment(PopUpFragment())
            }
            BOTTOM_POPOVER->{
                gotoFragment(BottomPopoverFragment())
            }
            ANIMATION->{
                gotoFragment(AnimationFragment())
            }
            TABLE ->{
                gotoFragment(TableFragment())
            }
            PERMISSION ->{
                gotoFragment(PermissionFragment())
            }
        }
    }

    private fun gotoFragment(fragment: Fragment){
        parentFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

}