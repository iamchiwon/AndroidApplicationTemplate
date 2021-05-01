package com.appfried.applicationtemplate.base

import androidx.lifecycle.ViewModel
import com.appfried.applicationtemplate.router.Router

typealias PropertyChangedNotifyObserver = () -> Unit

open class BaseViewModel : ViewModel() {
    var router: Router? = null

    private val observers = mutableListOf<PropertyChangedNotifyObserver>()

    fun addObserver(ob: PropertyChangedNotifyObserver) {
        observers.add(ob)
    }

    fun removeObserver(ob: PropertyChangedNotifyObserver) {
        observers.remove(ob)
    }

    fun notifyPropertyChanged() {
        observers.forEach { it() }
    }
}