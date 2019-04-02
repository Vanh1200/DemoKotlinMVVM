package com.example.demokotlinmvvm.data.source.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import com.example.demokotlinmvvm.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE userName LIKE :userName  and password LIKE :password")
    fun checkLogin(userName: String, password: String): LiveData<List<User>>

    @Insert(onConflict = IGNORE)
    fun insertUser(user: User)

    @Insert
    fun insertUsers(users: List<User>)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}
