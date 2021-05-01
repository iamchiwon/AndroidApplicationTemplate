package com.appfried.applicationtemplate.pages.second

import android.content.Intent
import android.os.Bundle
import com.appfried.applicationtemplate.R
import com.appfried.applicationtemplate.base.BaseActivity
import com.appfried.applicationtemplate.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity(R.layout.activity_second) {

    lateinit var binding:ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createDataBinding()
        binding.lifecycleOwner = this
        binding.activity = this
    }


    fun onSend() {
        val text = binding.inputField.text.toString()
        setResult(RESULT_OK, Intent().putExtra("text", text))
        finish()
    }
}