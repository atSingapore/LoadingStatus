package com.udacity

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoadingViewModel(private val app: Application) : AndroidViewModel(app) {

//    // Make selected URL live data to be observed by MainActivity
//    // MainActivity will download the selected URL
//    private val _selectedURL = MutableLiveData<String>()
//    val selectedURL: LiveData<String>
//    get() = _selectedURL
//
//    fun onGlideSelected(view: View)
//    {
//        _selectedURL.value = "https://github.com/bumptech/glide/archive/refs/heads/master.zip"
//    }
//
//    fun onLoadAppSelected(view: View)
//    {
//        _selectedURL.value = "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/refs/heads/master.zip"
//    }
//
//    fun onRetrofitSelected(view: View)
//    {
//        _selectedURL.value = "https://github.com/square/retrofit/archive/refs/heads/master.zip"
//    }
}