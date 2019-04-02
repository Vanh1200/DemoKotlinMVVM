package com.example.demokotlinmvvm.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.demokotlinmvvm.BR

abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> :
    AppCompatActivity() {

    lateinit var viewBinding: ViewBinding
    lateinit var viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = obtainViewModel()
        viewBinding.setVariable(BR.viewModel, viewModel)
    }

    abstract fun obtainViewModel(): ViewModel

}
