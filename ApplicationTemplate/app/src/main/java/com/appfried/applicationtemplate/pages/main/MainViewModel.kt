package com.appfried.applicationtemplate.pages.main

import android.app.Activity.RESULT_OK
import androidx.databinding.ObservableArrayList
import com.appfried.applicationtemplate.base.BaseViewModel
import com.appfried.applicationtemplate.router.Pages
import java.util.*

class MainViewModel : BaseViewModel() {
    private val items = ObservableArrayList<ListModel>()

    fun count() = items.size
    fun item(index: Int): ListModel? = items[index]

    fun onInputText() {
        router?.pushForResult(Pages.Second) { resultCode, data ->
            if (resultCode != RESULT_OK) return@pushForResult
            data
                ?.getStringExtra("text")
                ?.run { addItem(this) }
        }
    }

    private fun addItem(text: String) {
        items.add(ListModel(text, Date()))
        notifyPropertyChanged()
    }
}