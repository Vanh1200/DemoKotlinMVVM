package com.example.demokotlinmvvm.data.source

import android.arch.lifecycle.LiveData
import com.example.demokotlinmvvm.data.model.User

interface UserDataSource {
    interface Local {
        fun getAllUsers(): LiveData<List<User>>

        fun checkLogin(user: User): LiveData<List<User>>

        fun insertUser(user: User)

        fun insertUsers(users: List<User>)

        fun deleteUser(user: User)

        fun updateUser(user: User)
    }

    interface Remote

}
