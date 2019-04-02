package com.example.demokotlinmvvm.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.demokotlinmvvm.data.repository.UserRepository
import com.example.demokotlinmvvm.ui.signin.LoginViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(userRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
            }
        } as T

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(userRepository: UserRepository) =
            INSTANCE ?: ViewModelFactory(userRepository)
                .also { INSTANCE = it }

    }

}
