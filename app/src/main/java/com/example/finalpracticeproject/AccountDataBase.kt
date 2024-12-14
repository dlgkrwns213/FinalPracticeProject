package com.example.finalpracticeproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AccountDataBase: RoomDatabase() {
    abstract fun AccountDao(): AccountDao
    companion object {
        @Volatile
        private var INSTANCE: AccountDataBase? = null

        fun getInstance(context: Context): AccountDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AccountDataBase::class.java,
                    "account_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}