package com.example.finalpracticeproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val accountDao: AccountDao

    init {
        val database = AccountDataBase.getInstance(application)
        accountDao = database.AccountDao()
    }

    private var _name = MutableLiveData("")
    val name: LiveData<String>
        get() = _name

    private var _memo = MutableLiveData("")
    val memo: LiveData<String>
        get() = _memo

    private var _money = MutableLiveData(0)
    val money: LiveData<Int>
        get() = _money

    fun setName(inputName: String?) {
        _name.value = inputName ?: ""
    }

    fun setMemo(inputMemo: String) {
        _memo.value = inputMemo
    }


    fun setMoney(inputMoney: Int) {
        _money.value = inputMoney
    }

    fun record(pm: Int) {
        val name = name.value ?: return
        val money = money.value ?: return

        val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        Log.v("mainViewModel", "${pm}${money} $name $formattedDate")

        val newLog = Account(name=name, money=pm*money, date=formattedDate, memo = memo.value ?: "")
        viewModelScope.launch(Dispatchers.IO) {
            accountDao.insertLog(newLog)
        }
    }

    fun deleteTableData() {
        viewModelScope.launch {
            accountDao.deleteAll()
        }
    }
}