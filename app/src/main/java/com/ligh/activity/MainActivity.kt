package com.ligh.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.Scroller
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ligh.R
import com.ligh.base.NavigationFragment
import com.ligh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    lateinit var  dialog :Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)


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


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {//返回键
            Toast.makeText(this, "F2", Toast.LENGTH_SHORT).show()
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {//HOME键
            Toast.makeText(this, "F3", Toast.LENGTH_SHORT).show()
        } else if (keyCode == KeyEvent.KEYCODE_NUMPAD_0) {//数字键
            Toast.makeText(this, "F4", Toast.LENGTH_SHORT).show()
        }
        return super.onKeyDown(keyCode, event)
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

    private fun showPopUpWindow(){
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

            //动画实现
            animation()
        }

    }

    /**
     * 瞬移动画，还可以通过设置一个空白的view控制宽高来实现
     */
    private fun animation(){
        //通过translationX实现
        val view = bind.btDialog
//        var translationX = view.translationX
//        var translationY = view.translationY
//        Log.i(Companion.TAG, "animation: pre x = $translationX  $translationY")
//        Log.i(Companion.TAG, "animation: pre x = ${view.x}  ${view.y}")
//        translationY += 100
//        translationX += 100
//        view.translationY = translationY
//        view.translationX = translationX
//        Log.i(Companion.TAG, "animation: after x = $translationX  $translationY")
//        Log.i(Companion.TAG, "animation: pre x = ${view.x}  ${view.y}")

        //通过scroller实现,注意这种方式并不会改变view的布局参数，只是视觉上变化
        view.isScrollContainer = true //不设置scrollTo 无效
        view.scrollTo(view.scrollX + 100, view.scrollY + 100)
        Log.i(TAG, "animation: scrollTo  after scroll x = ${view.scrollX}  ${view.scrollY}")
        Log.i(TAG, "animation: scrollTo  after x = ${view.x}  ${view.y}")
    }

    /**
     * 弹性动画
     */
    private fun animationDynamic(){
        //1 通过post等方式持续设置translationX，Y
        //2 通过scroller
        val scroller = Scroller(this)
        val view = bind.btDialog
        scroller.startScroll(view.scrollX,view.scrollY,100,100,1000) // 实际上就是设置参数
        view.invalidate() // 重新绘制，会调用computeScroll方法进行弹性实现，实际就是根据设置参数百分比
        //注意要重载view的computeScroll
//        @Override
//        public void computeScroll() {
//            if (mScroller.computeScrollOffset()) { // computeScrollOffset 返回true表明还没有绘制结束，没有到指定位置
//                int y = mScroller.getCurrY();
//                int dy = y - mLastY;
//                mLastY = y;
//                scrollBy(0, dy);
//                postInvalidate();
//            }
//        }
    }

    companion object {
        const val TAG = "Test Activity"
    }

}