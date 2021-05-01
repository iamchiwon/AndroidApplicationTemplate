package com.appfried.applicationtemplate

import android.app.Application
import android.content.Intent
import com.appfried.applicationtemplate.pages.main.MainActivity
import com.appfried.applicationtemplate.pages.second.SecondActivity
import com.appfried.applicationtemplate.router.Pages
import com.appfried.applicationtemplate.router.RouterContainer

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initRouting()
    }
}

fun initRouting() {
    RouterContainer.registActivity(Pages.Main) { Intent(it, MainActivity::class.java) }
    RouterContainer.registActivity(Pages.Second) { Intent(it, SecondActivity::class.java) }
}