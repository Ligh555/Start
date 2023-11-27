package com.ligh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R
import com.ligh.binding
import com.ligh.databinding.ActivityMainBinding
import com.ligh.json.JsonTest
import com.ligh.widget.BiometricTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewBinding: ActivityMainBinding by binding()

    companion object {
        const val TAG = "Test Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            JsonTest().test()
        }
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {//返回键
                return if (supportFragmentManager.backStackEntryCount < 1) {
                    AlertDialog.Builder(this)
                        .setTitle("退出应用")
                        .setMessage("确定要退出应用吗？")
                        .setPositiveButton("确定") { _, _ ->
                            finish()
                        }
                        .setNegativeButton("取消", null)
                        .show()
                    true
                } else {
                    supportFragmentManager.popBackStack()
                    true
                }
            }

            KeyEvent.KEYCODE_HOME -> {//HOME键
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
            }

            KeyEvent.KEYCODE_NUMPAD_0 -> {//数字键
                Toast.makeText(this, "F4", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.test, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 10
        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }


    private class CustomAdapter(private val data: List<String>) : BaseAdapter() {
        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): String {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return data[position].hashCode().toLong()
        }

        override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(container.context)
                .inflate(R.layout.item_lv_multiple_choice, container, false)
            (view.findViewById<View>(R.id.tv_content) as TextView).text = getItem(position)
            return view
        }
    }

    fun initRecycleView() {
        viewBinding.recycleView.apply {
            val dte = MyAdapter()
            adapter = dte
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            post {
                val layoutManager = viewBinding.recycleView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                var fullyVisibleItemCount = 0
                for (position in firstVisibleItemPosition..lastVisibleItemPosition) {
                    val view = layoutManager.findViewByPosition(position)
                    val itemWidth = view?.width ?: 0
                    val itemRight = view?.let { layoutManager.getDecoratedRight(it) } ?: 0
                    val recyclerViewRight =
                        viewBinding.recycleView.width - viewBinding.recycleView.paddingRight

                    if (itemRight <= recyclerViewRight) {
                        fullyVisibleItemCount++
                    } else {
                        break // 超出屏幕范围，停止计数
                    }
                }
                Log.i("lighrecy", "onCreate1:  $fullyVisibleItemCount")
            }
        }
    }

    private fun initListView() {
        with(viewBinding.lvTest) {
            val data = (0..10).map { "测试数据$it" }
            adapter = CustomAdapter(data)
            setOnItemClickListener { parent, view, position, id ->
                val checkedItemPositions: SparseBooleanArray =
                    getCheckedItemPositions()
                val isChecked = checkedItemPositions[position]
                Toast.makeText(
                    this@MainActivity,
                    "item $position isChecked=$isChecked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}