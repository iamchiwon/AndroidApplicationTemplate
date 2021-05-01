package com.appfried.applicationtemplate.router

import android.content.Intent
import com.appfried.applicationtemplate.base.BaseActivity

enum class Pages {
    Main, Second
}

class Router {

    private var baseActivity: BaseActivity? = null

    constructor(base: BaseActivity) {
        this.baseActivity = base
    }

    fun push(target: Pages) {
        baseActivity?.let { base ->
            val intent = RouterContainer.resolveActivity(base, target)
            base.startActivity(intent)
        }
    }

    fun pushForResult(target: Pages, result: (Int, Intent?) -> Unit) {
        baseActivity?.let { base ->
            val randomRequestCode = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
            val intent = RouterContainer.resolveActivity(base, target)
            base.addResultCallback { requestCode, resultCode, data ->
                if (randomRequestCode != requestCode) return@addResultCallback false
                base.post { result(resultCode, data) }
                true
            }
            base.startActivityForResult(intent, randomRequestCode)
        }
    }

}