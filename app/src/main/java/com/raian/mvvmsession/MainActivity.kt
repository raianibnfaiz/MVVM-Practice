package com.raian.mvvmsession

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.raian.mvvmsession.viewModel.BlogViewModel
import com.raian.mvvmsession.viewModel.BlogViewModelProvideFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var button : Button
    private lateinit var  viewModel : BlogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        val factory = BlogViewModelProvideFactory()
        viewModel = ViewModelProvider(this,factory).get(BlogViewModel::class.java)
    }
    
}