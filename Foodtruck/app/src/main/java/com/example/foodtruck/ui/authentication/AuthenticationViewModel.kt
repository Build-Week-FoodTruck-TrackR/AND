package com.example.foodtruck.ui.authentication

import android.app.Application
import androidx.lifecycle.*
import com.example.foodtruck.data.source.local.model.AccountType
import com.example.foodtruck.data.source.local.model.AuthenticationState
import com.example.foodtruck.data.source.local.model.City
import com.example.foodtruck.data.source.local.model.NewUser
import com.example.foodtruck.data.source.local.model.firebase_models.User
import com.example.foodtruck.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.launch

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AuthRepository = AuthRepository()
    private val _uid: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val uid: LiveData<String>
        get() = _uid

    private val _authenticationState: MutableLiveData<AuthenticationState> by lazy {
        MutableLiveData<AuthenticationState>()
    }
    val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState


    fun isUserLoggedIn() {
        when (repository.isUserLoggedIn()) {
            true -> {
                _authenticationState.postValue(AuthenticationState.Authenticated)
            }
            false -> {
                _authenticationState.postValue(AuthenticationState.Failed)
            }
        }
    }

    fun authenticateUser(email: String, password: String) = viewModelScope.launch {
        try {
            repository.authenticateWithEmailAndPassword(email, password)?.let {
                _authenticationState.postValue(AuthenticationState.Authenticated)
            } ?: run {
                _authenticationState.postValue(
                    AuthenticationState.Failed
                )
            }
        } catch (e: FirebaseAuthException) {
            _authenticationState.postValue(
                AuthenticationState.Failed
            )
        }
    }
    fun signOutUser() {
        repository.logout()
    }
    fun registerUser(email: String, password: String, city: City, username: String, accountType: AccountType) = viewModelScope.launch {
        try {
            repository.registerWithEmailAndPassword(email, password)?.let {
                val newUser = User(it, email, username, accountType, city)
                repository.addNewUserTodatabase(newUser)
                _uid.postValue(it)
                _authenticationState.postValue(AuthenticationState.Authenticated)
            } ?: run {
                _authenticationState.postValue(
                    AuthenticationState.Failed
                )
            }
        } catch (e: FirebaseAuthException) {
            _authenticationState.postValue(
                AuthenticationState.Failed
            )
        }
    }
}