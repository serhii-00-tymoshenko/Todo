package com.mintokoneko.todo.ui.profile.view_model

import com.mintokoneko.todo.base.BaseViewModel
import com.mintokoneko.todo.data.User
import com.mintokoneko.todo.repositories.UserRepository

class ProfileViewModel(
    private val userRepository: UserRepository
): BaseViewModel() {
    fun setUserDetails(user: User) = userRepository.putUserDetails(user)

    fun getUserDetails(): User = userRepository.getUserDetails()
}