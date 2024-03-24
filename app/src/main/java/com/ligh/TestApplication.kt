package com.ligh

import android.app.Application
import com.bytedance.tools.codelocator.CodeLocator
import com.bytedance.tools.codelocator.config.CodeLocatorConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CodeLocator.config(
            CodeLocatorConfig.Builder()
                .enableHookInflater(true)
                .build()
        )
    }
}