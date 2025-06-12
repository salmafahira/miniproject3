package com.salmafahira0038.miniproject3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject3.model.MakeUp
import com.salmafahira0038.miniproject3.network.MakeupApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<MakeUp>())
        private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               data.value = MakeupApi.service.getMakeup()
            }catch (e:Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}