package com.example.demokotlinmvvm.data.source.local

import com.example.demokotlinmvvm.data.model.User
import com.example.demokotlinmvvm.data.source.UserDataSource
import com.example.demokotlinmvvm.data.source.local.dao.UserDao

class UserLocalDataSource(
    private val userDao: UserDao
) : UserDataSource.Local {
    override fun checkLogin(user: User) = userDao.checkLogin(user.userName, user.password)

    override fun getAllUsers() = userDao.getUsers()

    override fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun insertUsers(users: List<User>) {
        userDao.insertUsers(users)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun updateUser(user: User) {
        userDao.updateUser(user)
    }

}
