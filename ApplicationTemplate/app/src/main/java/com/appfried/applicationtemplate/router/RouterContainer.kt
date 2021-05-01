package com.appfried.applicationtemplate.router

import android.content.Context
import android.content.Intent

typealias ActivityMakerType = (Context) -> Intent

object RouterContainer {

    private val activityMakers = mutableMapOf<Pages, ActivityMakerType>()

    fun registActivity(name: Pages, maker: ActivityMakerType) {
        activityMakers[name] = maker
    }

    fun resolveActivity(context: Context, name: Pages): Intent {
        return activityMakers[name]?.invoke(context) ?: throw Exception("regist activity first")
    }
}