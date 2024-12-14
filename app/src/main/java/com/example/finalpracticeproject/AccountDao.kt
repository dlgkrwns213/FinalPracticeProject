package com.example.finalpracticeproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.finalpracticeproject.AccountDao

@Dao
interface AccountDao {
    @Insert
    fun insertLog(log: Account)

    @Query("SELECT * FROM ACCOUNT WHERE name == :name")
    fun getMoneyList(name: String): LiveData<List<Account>>

    @Query("SELECT SUM(money) FROM Account WHERE name == :name")
    fun getMoneySum(name: String): LiveData<Int>

    @Query("SELECT * FROM Account WHERE name == :name AND date LIKE :date || '%'")
    fun getMoneyListByDate(name: String, date: String): LiveData<List<Account>>

    @Query("SELECT SUM(money) FROM Account WHERE name == :name AND date LIKE :date || '%'")
    fun getMoneySumByDate(name: String, date: String): LiveData<Int>

    @Query("Delete From Account")
    fun deleteAll()
}