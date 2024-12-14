package com.example.finalpracticeproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class Account (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val date: String,
    val money: Int,
    val memo: String
)
