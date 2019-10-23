package com.example.foodtruck.ui.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodtruck.data.source.local.model.entities.User
import com.example.foodtruck.data.source.local.room.UserRoomDatabase
import com.example.foodtruck.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    // Reference to AuthRepository
    private val authRepository: AuthRepository
    // LiveData gives us updated list of Users as they change
    private val allCachedUsers: LiveData<List<User>>
    private val currentUser: LiveData<User>

    init {
        val userDao = UserRoomDatabase.getDatabase(application).userDao()
        authRepository = AuthRepository(userDao)
        allCachedUsers = authRepository.cachedUsers
        currentUser = authRepository.currentUser
    }

    fun insertUser(user: User) = viewModelScope.launch {
        authRepository.insertUser(user)
    }
    fun setCurrentUser(userId: Int) = viewModelScope.launch {
        authRepository.updateCurrentUser(userId)
    }
}