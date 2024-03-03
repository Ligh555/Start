package com.ligh.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
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
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ligh.R
import com.ligh.binding
import com.ligh.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


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
        Test.test()
        test()

        startActivity(Intent(this,MainActivity2::class.java))
    }

    private fun test(){
        viewBinding.btTest.setOnClickListener {
            Toast.makeText(this@MainActivity,"测试",Toast.LENGTH_SHORT).show()
            createNotificationForNormal()
        }
    }

    private fun createNotificationForNormal() {

        val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val mNormalChannelId = "123"
        // 适配8.0及以上 创建渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                mNormalChannelId,
                "测试",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "描述"
                setShowBadge(false) // 是否在桌面显示角标
            }
            notificationManager.createNotificationChannel(channel)
        }

        // 构建配置
        val mBuilder = NotificationCompat.Builder(this, mNormalChannelId)
            .setContentTitle("普通通知") // 标题
            .setContentText("普通通知内容") // 文本
            .setSmallIcon(R.mipmap.ic_launcher) // 小图标
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher))
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 7.0 设置优先级
            .setAutoCancel(true) // 是否自动消失（点击）or mManager.cancel(mNormalNotificationId)、cancelAll、setTimeoutAfter()
        // 发起通知
        notificationManager.notify(123, mBuilder.build())
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