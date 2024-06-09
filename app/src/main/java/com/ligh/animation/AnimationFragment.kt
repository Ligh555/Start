package com.ligh.animation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import androidx.fragment.app.Fragment
import com.ligh.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment() {

    private lateinit var bind :FragmentAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        bind = FragmentAnimationBinding.inflate(inflater,container,false)
        initView()
        return bind.root
    }

    private fun initView(){
        bind.tvButton.setOnClickListener {
            animation()
        }

        bind.tvButtonFollow.setOnClickListener {
            animationDynamic()
        }
    }

    /**
     * 弹性动画
     */
    private fun animationDynamic() {
        //1 通过post等方式持续设置translationX，Y
        //2 通过scroller
        val scroller = Scroller(this.context)
        val view = bind.tvButtonFollow
        scroller.startScroll(view.scrollX, view.scrollY, 100, 100, 1000) // 实际上就是设置参数
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

    /**
     * 瞬移动画(注意这种只能改变布局内容的位置，并不能改变view位置)，还可以通过设置一个空白的view控制宽高或者调整布局参数来实现
     */
    private fun animation() {
        //通过translationX实现
        val view = bind.tvButton
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
        Log.i("AnimationFragment", "animation: scrollTo  after scroll x = ${view.scrollX}  ${view.scrollY}")
        Log.i("AnimationFragment", "animation: scrollTo  after x = ${view.x}  ${view.y}")
    }


}