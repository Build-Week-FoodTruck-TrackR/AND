package com.example.foodtruck.ui.authentication

import android.app.Application
import androidx.lifecycle.*
import com.example.foodtruck.data.source.local.model.NewUser
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.room.UserRoomDatabase
import com.example.foodtruck.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthenticationViewModel(private val savedStateHandle: SavedStateHandle, private val repository: AuthRepository) : ViewModel() {

    companion object {
        private const val USER_KEY = "userId"
    }

    private val _userId  : MutableLiveData<Int> = savedStateHandle.getLiveData(USER_KEY)
    val userId : LiveData<Int> = _userId

    fun saveCurrentUser(userId: Int) {
        savedStateHandle.set(USER_KEY, userId)
    }

    fun authenticateNewUser(newUser: NewUser) {
    }

    fun deleteCurrentUser() {
        savedStateHandle.remove<Int>(USER_KEY)
    }

    fun getCurrentUser(): String {
        return savedStateHandle.get(USER_KEY) ?: ""
    }
}