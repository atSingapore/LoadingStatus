package com.udacity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.udacity.databinding.ContentMainBinding

//class LoadingFragment : Fragment() {
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val binding: ContentMainBinding = DataBindingUtil.inflate(
//            inflater, R.layout.content_main, container, false
//        )
//
//        val viewModel = ViewModelProviders.of(this).get(LoadingViewModel::class.java)
//
//        binding.loadingViewModel = viewModel
//        binding.lifecycleOwner = this.viewLifecycleOwner
//
//        return binding.root
//    }
//
//    companion object {
//        fun newInstance() = LoadingFragment()
//    }
//
//}