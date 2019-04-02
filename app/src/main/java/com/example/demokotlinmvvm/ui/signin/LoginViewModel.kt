package com.example.demokotlinmvvm.ui.signin

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.demokotlinmvvm.base.BaseViewModel
import com.example.demokotlinmvvm.data.model.User
import com.example.demokotlinmvvm.data.repository.UserRepository

class LoginViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {
    val usersLiveData: LiveData<List<User>> = userRepository.getAllUsers()
    val isLoginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun signUp(userName: String, password: String) {
        userRepository.insertUser(User(userName, password))
    }

    fun login(userName: String, password: String) {
        isLoginSuccess.value = false
        for (user in usersLiveData.value!!) {
            if (user.userName == userName && user.password == password) {
                isLoginSuccess.value = true
            }
        }
    }
}
