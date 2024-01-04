package com.ligh.permission

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.ligh.R
import com.ligh.databinding.FragmentPermissionBinding


class PermissionFragment : Fragment() {

    private lateinit var bind: FragmentPermissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentPermissionBinding.inflate(inflater, container, false)
        initView()
        return bind.root
    }

    private fun initView(){
        bind.btPermissionApply.setOnClickListener(){
            test()
        }
    }

    private fun test(){
        //请求读取存储空间权限和定位权限
        requestMultiplePermissions.launch(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION))
        this.isAdded
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
        permissions.entries.forEach {
            val permissionName = it.key
            if (it.value) {
                //同意授权
                Log.d("-,-,-", "$permissionName granted")
            } else {
                Log.d("-,-,-", "$permissionName not granted")
                //未同意授权
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(permissionName)) {
                        //用户拒绝权限并且系统不再弹出请求权限的弹窗
                        //这时需要我们自己处理，比如自定义弹窗告知用户为何必须要申请这个权限
                        Log.d("-,-,-", "$permissionName not granted and should not show rationale")
                    }
                }
            }
        }
    }

}