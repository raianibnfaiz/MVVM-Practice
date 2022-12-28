package com.raian.mvvmsession

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raian.mvvmsession.adapter.BlogRecyclerAdapter
import com.raian.mvvmsession.databinding.ActivityMainBinding
import com.raian.mvvmsession.model.Blog
import com.raian.mvvmsession.viewModel.BlogViewModel
import com.raian.mvvmsession.viewModel.BlogViewModelProvideFactory
import java.text.SimpleDateFormat
import java.util.*

//class MainActivity : AppCompatActivity() {
//    private lateinit var recyclerView : RecyclerView
//    private lateinit var button : Button
//    private lateinit var  viewModel : BlogViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        recyclerView = findViewById(R.id.recycler)
//        val factory = BlogViewModelProvideFactory()
//        viewModel = ViewModelProvider(this,factory).get(BlogViewModel::class.java)
//    }
//
//}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var layoutManager = LinearLayoutManager(this)
    private lateinit var viewModel: BlogViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.recycler)
        val factory = BlogViewModelProvideFactory()
        viewModel = ViewModelProvider(this, factory)[BlogViewModel::class.java]

        binding.button.setOnClickListener{
            addData()
            binding.titletxt.text.clear()
            initializeAdapter()
        }
    }
    private fun addData(){
        val title = binding.titletxt.text.toString()
        val blog = Blog(title, getDate())
        viewModel.addBlog(blog)
        recyclerView.adapter?.notifyDataSetChanged()
    }
    private fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }
    private fun initializeAdapter(){
        recyclerView.layoutManager = layoutManager
        observeData()
    }
    private fun observeData(){
        viewModel.list.observe(this, androidx.lifecycle.Observer {
            recyclerView.adapter = BlogRecyclerAdapter(this, viewModel, it)
        })
    }

}