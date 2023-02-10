package com.example.exchangeabletoken.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "$it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PageViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    private fun getTransactionData() {

    }
}