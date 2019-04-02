package com.example.demokotlinmvvm.data.repository

import com.example.demokotlinmvvm.data.model.User
import com.example.demokotlinmvvm.data.source.UserDataSource
import com.example.demokotlinmvvm.data.source.local.UserLocalDataSource
import com.example.demokotlinmvvm.data.source.remote.UserRemoteDataSource

class UserRepository private constructor(
    val userLocalDataSource: UserLocalDataSource,
    val userRemoteDataSource: UserRemoteDataSource
) : UserDataSource.Local, UserDataSource.Remote {
    override fun getAllUsers() =
        userLocalDataSource.getAllUsers()

    override fun checkLogin(user: User) =
        userLocalDataSource.checkLogin(user)

    override fun insertUser(user: User) =
        userLocalDataSource.insertUser(user)

    override fun insertUsers(users: List<User>) =
        userLocalDataSource.insertUsers(users)

    override fun deleteUser(user: User) =
        userLocalDataSource.deleteUser(user)

    override fun updateUser(user: User) =
        userLocalDataSource.updateUser(user)


    companion object {

        private var INSTANCE: UserRepository? = null
        fun getInstance(
            userLocalDataSource: UserLocalDataSource,
            userRemoteDataSource: UserRemoteDataSource
        ) =
            INSTANCE ?: synchronized(UserRepository::class.java) {
                INSTANCE ?: UserRepository(userLocalDataSource, userRemoteDataSource)
                    .also { INSTANCE = it }
            }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}
