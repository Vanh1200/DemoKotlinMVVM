package com.example.demokotlinmvvm.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var userName: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //or Int? = null
}
