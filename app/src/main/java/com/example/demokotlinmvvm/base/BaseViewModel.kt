package com.example.demokotlinmvvm.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { false }
    val errorMessage = MutableLiveData<String>()


    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

}
