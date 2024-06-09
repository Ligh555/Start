package com.ligh.notice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.ligh.R

object NoticeUtils {

    fun createNotificationForNormal(context :Context) {

        val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

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
        val mBuilder = NotificationCompat.Builder(context, mNormalChannelId)
            .setContentTitle("普通通知") // 标题
            .setContentText("普通通知内容") // 文本
            .setSmallIcon(R.mipmap.ic_launcher) // 小图标
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 7.0 设置优先级
            .setAutoCancel(true) // 是否自动消失（点击）or mManager.cancel(mNormalNotificationId)、cancelAll、setTimeoutAfter()
        // 发起通知
        notificationManager.notify(123, mBuilder.build())
    }
}