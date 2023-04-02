package com.ligh.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.PopupWindow
import androidx.activity.viewModels
import com.ligh.R
import com.ligh.databinding.ActivityMainBinding
import com.ligh.viewModul.TestViewModul

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<TestViewModul>()

     lateinit var  dialog :Dialog

    lateinit var bind : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        viewModel.mutableLiveData.observe(this
        ) { t -> t?.let {
            bind.tvRemaim.setText(it)
        } }


        bind.tvRemaim.setOnClickListener(){
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.view_test)
           this.window.decorView.tag = dialog
            val dia = Dialog(this)
            val  view  = LayoutInflater.from(this).inflate(R.layout.view_test_teal,null,false)
            view.setOnClickListener(){
                dia.dismiss()
                val test = this.window.decorView.tag as Dialog
                test.show()
            }
            dia.setContentView(view)
            dia.show()
        }
    }

    override fun onResume() {
        super.onResume()
        showDialog()
        showPopUpWindow()
    }

    fun showDialog(){
        dialog = Dialog(this)
        bind.btDialog.setOnClickListener{
            if(dialog.isShowing){
                dialog.dismiss()
            }
            val view = LayoutInflater.from(this).inflate(R.layout.view_test_teal,null,false)
            view.setOnClickListener{
                dialog.dismiss()
            }
            dialog.setContentView(view)


            val window = dialog .window
            window?.setGravity(Gravity.BOTTOM);
            dialog.show()
        }
    }

    fun showPopUpWindow(){
        val popupWindow = PopupWindow(this)
        val view = LayoutInflater.from(this).inflate(R.layout.view_test_teal,null,false)
        view.setOnClickListener{
            popupWindow.dismiss()
            bind.backgroundView.visibility = View.GONE
        }
        popupWindow.contentView = view
        //点击外部消失，这里因为PopupWindow填充了整个窗口，所以这句代码就没用了
        popupWindow.isOutsideTouchable = true
        //设置可以点击
        popupWindow.isTouchable = true
        //去掉阴影
        popupWindow.elevation = 0F
        //去掉边框
        popupWindow.setBackgroundDrawable(ColorDrawable(0))
        bind.btDialog.setOnClickListener {
            //通过半透明view实现背景置灰
            bind.backgroundView.visibility = View.VISIBLE
            popupWindow.showAtLocation(bind.root, Gravity.BOTTOM, 0, 0)
        }

    }

}