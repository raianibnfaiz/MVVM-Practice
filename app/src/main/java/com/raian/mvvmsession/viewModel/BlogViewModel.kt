package com.raian.mvvmsession.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raian.mvvmsession.model.Blog

class BlogViewModel : ViewModel() {

    val list = MutableLiveData<ArrayList<Blog>>()
    val newList = arrayListOf<Blog>()


    fun addBlog(blog: Blog){
        newList.add(blog)
        list.value = newList
    }
    fun updateBlog(position:Int, blog:Blog){
        newList.set(position,blog)
        list.value = newList
    }

    fun removeBlog(blog:Blog){
        newList.remove(blog)
        list.value = newList
    }
}