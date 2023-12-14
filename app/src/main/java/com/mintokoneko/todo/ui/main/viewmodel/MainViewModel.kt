package com.mintokoneko.todo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mintokoneko.todo.base.BaseViewModel
import com.mintokoneko.todo.data.User
import com.mintokoneko.todo.repositories.UserRepository

class MainViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _userDetails = MutableLiveData<User>()
    val userDetails: LiveData<User> get() = _userDetails

    init {
        setUserDetails()
        setUserDetailsListener()
    }

    private fun setUserDetailsListener() =
        userRepository.setUserDetailsListener { setUserDetails() }


    private fun setUserDetails() {
        _userDetails.value = getUserDetails()
    }

    private fun getUserDetails() = userRepository.getUserDetails()
}