package com.salmafahira0038.miniproject3.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject3.network.MakeupApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = MakeupApi.service.getMakeup()
                Log.d("MainViewModel", "Success: $result")
            }catch (e:Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}