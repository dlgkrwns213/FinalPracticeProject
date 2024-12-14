package com.example.finalpracticeproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.annotations.ApiStatus.NonExtendable

class ShowViewModel(application: Application): AndroidViewModel(application) {
    private val accountDao: AccountDao

    init {
        val database = AccountDataBase.getInstance(application)
        accountDao = database.AccountDao()
    }

    private var _name = MutableLiveData("")
    private val name: LiveData<String>
        get() = _name

    fun setName(inputName: String) {
        _name.value = inputName
    }

    fun showList(): LiveData<List<Account>> {
        return accountDao.getMoneyList(name.value?:"")
    }
}