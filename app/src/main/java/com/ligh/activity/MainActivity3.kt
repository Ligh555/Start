package com.ligh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.google.android.material.tabs.TabLayout
import com.ligh.R
import com.ligh.binding
import com.ligh.databinding.ActivityMain3Binding
import com.ligh.databinding.ActivityMainBinding

class MainActivity3 : BaseActivity() {

    override val viewBinding: ActivityMain3Binding by binding()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    override fun onResume() {
        super.onResume()
        initTabLayout()
    }

    private fun initTabLayout() {

        repeat(3) {
            val tab = viewBinding.tabLayout1.newTab()
//            var textView = TextView(this).apply {
//                text = "测试$it"
//            }
//            tab.customView = textView
            tab.text = "测试$it"
            viewBinding.tabLayout1.addTab(tab)
        }
        repeat(3) {
            val tab = viewBinding.tabLayout6.newTab()
//            var textView = TextView(this).apply {
//                text = "测试$it"
//            }
//            tab.customView = textView
            tab.text = "测试$it"
            viewBinding.tabLayout6.addTab(tab)
        }
        repeat(3) {
            val tab = viewBinding.tab1.newTab()
//            var textView = TextView(this).apply {
//                text = "测试$it"
//            }
//            tab.customView = textView
            var str =""
            repeat(it+1){
                str += "测试$it"
            }
            tab.text = str
            viewBinding.tab1.addTab(tab)
        }

        viewBinding.tabLayout6.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Toast.makeText(this@MainActivity3, "select${tab.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Toast.makeText(this@MainActivity3, "un${tab.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                Toast.makeText(this@MainActivity3, "re${tab.text}", Toast.LENGTH_SHORT).show()
            }

        })
        viewBinding.button1.setOnClickListener {
            viewBinding.tab1.changeTheme()
        }

    }
}