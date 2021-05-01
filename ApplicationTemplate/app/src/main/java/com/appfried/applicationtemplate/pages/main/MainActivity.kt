package com.appfried.applicationtemplate.pages.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.appfried.applicationtemplate.R
import com.appfried.applicationtemplate.base.BaseActivity
import com.appfried.applicationtemplate.databinding.ActivityMainBinding
import com.appfried.applicationtemplate.databinding.ListItemBinding
import com.appfried.applicationtemplate.router.Router
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createDataBinding()
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.router = Router(this)
        binding.viewModel = viewModel

        adapter = Adapter(viewModel)
        binding.recyclerView.adapter = adapter
    }

    class Adapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<ViewHolder>() {

        init {
            viewModel.addObserver { notifyDataSetChanged() }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflator = LayoutInflater.from(parent.context)
            val binding: ListItemBinding =
                DataBindingUtil.inflate(inflator, R.layout.list_item, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setData(viewModel.item(position) ?: ListModel("Crashed!!", Date()))
        }

        override fun getItemCount(): Int {
            return viewModel.count()
        }
    }

    class ViewHolder(private val binder: ListItemBinding) : RecyclerView.ViewHolder(binder.root) {
        @SuppressLint("SimpleDateFormat")
        private val formatter = SimpleDateFormat("yyyy. MM. dd")
        fun setData(data: ListModel) {
            binder.text = data.text
            binder.createdAt = data.date.run { formatter.format(this) }
        }
    }
}
