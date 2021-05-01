package com.appfried.applicationtemplate.base

import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

typealias ActivityResultCallback = (requestCode: Int, resultCode: Int, data: Intent?) -> Boolean

abstract class BaseActivity(private val layoutId: Int) :
    AppCompatActivity() {

    private val handler = Handler()

    fun <DATA_BINDING : ViewDataBinding> createDataBinding(): DATA_BINDING {
        return DataBindingUtil.setContentView(this, layoutId)
    }

    private val activityResultCallbacks = mutableListOf<ActivityResultCallback>()

    fun addResultCallback(callback: ActivityResultCallback) {
        activityResultCallbacks.add(callback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (callback in activityResultCallbacks) {
            if (callback(requestCode, resultCode, data)) {
                activityResultCallbacks.remove(callback)
                break
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun post(runnable: Runnable) {
        handler.post(runnable)
    }

    fun postDelayed(runnable: Runnable, delay: Long) {
        handler.postDelayed(runnable, delay)
    }
}