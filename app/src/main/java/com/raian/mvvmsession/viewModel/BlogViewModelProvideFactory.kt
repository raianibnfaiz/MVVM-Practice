package com.raian.mvvmsession.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlogViewModelProvideFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(BlogViewModel::class.java)){
            return BlogViewModel()as T
        }
        throw IllegalAccessException("Unknown")
    }
}