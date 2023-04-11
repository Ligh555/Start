package com.ligh.widget

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.ligh.R
import com.ligh.databinding.FragmentBottomPopoverBinding

/**
 * 底部弹窗
 */
class BottomPopoverFragment : Fragment() {
   private  lateinit var bind : FragmentBottomPopoverBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       bind = FragmentBottomPopoverBinding.inflate(inflater,container,false)

        initView()

        return bind.root
    }
    private fun initView(){
        showPopUpWindow()
        showDialog()
    }

    private fun showDialog() {
        val dialog = Dialog(bind.btBottomPopoverDialog.context)
        bind.btBottomPopoverDialog.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
            val view = LayoutInflater.from(context).inflate(R.layout.view_test_teal, null, false)
            view.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setContentView(view)


            val window = dialog.window
            window?.setGravity(Gravity.BOTTOM)
            dialog.show()
        }
    }

    private fun showPopUpWindow() {
        val popupWindow = PopupWindow(context)
        val view = LayoutInflater.from(context).inflate(R.layout.view_test_teal, null, false)
        view.setOnClickListener {
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
        bind.btBottomPopoverPopup.setOnClickListener {
            //通过半透明view实现背景置灰
            bind.backgroundView.visibility = View.VISIBLE
            popupWindow.showAtLocation(bind.root, Gravity.BOTTOM, 0, 0)
        }

    }
}