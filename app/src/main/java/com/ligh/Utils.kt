package com.ligh

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


inline fun <reified VB : ViewBinding> Activity.binding() = lazy {
    inflateBinding<VB>(layoutInflater).also { binding ->
        setContentView(binding.root)
    }
}

inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB


val isDeviceRooted: String
    get() = (checkRootMethod1() || checkRootMethod2() || checkRootMethod3()).toString()

private fun checkRootMethod1(): Boolean {
    val buildTags = Build.TAGS
    return buildTags != null && buildTags.contains("test-keys")
}

private fun checkRootMethod2(): Boolean {
    val paths = arrayOf(
        "/system/app/Superuser.apk",
        "/sbin/su",
        "/system/bin/su",
        "/system/xbin/su",
        "/data/local/xbin/su",
        "/data/local/bin/su",
        "/system/sd/xbin/su",
        "/system/bin/failsafe/su",
        "/data/local/su",
        "/su/bin/su"
    )
    for (path in paths) {
        if (File(path).exists()) return true
    }
    return false
}

private fun checkRootMethod3(): Boolean {
    var process: Process? = null
    return try {
        process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
        BufferedReader(InputStreamReader(process.inputStream)).readLine() != null
    } catch (t: Throwable) {
        false
    } finally {
        process?.destroy()
    }
}