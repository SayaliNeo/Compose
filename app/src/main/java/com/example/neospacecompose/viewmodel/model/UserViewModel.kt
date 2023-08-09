package com.example.neospacecompose.viewmodel.model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neospacecompose.model.UserDetails
import com.example.neospacecompose.repository.APIService
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _userList = mutableStateListOf<UserDetails>()
    var errorMessage: String by mutableStateOf("")

    val userList: List<UserDetails>
        get() = _userList

    fun userDetailsList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()

            try {
                _userList.clear()
                _userList.addAll(apiService.getLisDetails())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }


}