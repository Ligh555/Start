package com.ligh.activity

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ligh.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {//返回键
                return if (supportFragmentManager.backStackEntryCount < 1) {
                    AlertDialog.Builder(this)
                        .setTitle("退出应用")
                        .setMessage("确定要退出应用吗？")
                        .setPositiveButton("确定"){ _, _ ->
                            finish()
                        }
                        .setNegativeButton("取消",null)
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


    companion object {
        const val TAG = "Test Activity"
    }

}