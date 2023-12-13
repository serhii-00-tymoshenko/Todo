package com.mintokoneko.todo.base

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class BaseViewModelProvider {
    inline fun <reified T : BaseViewModel> getViewModel(
        owner: ViewModelStoreOwner,
        baseRepository: BaseRepository
    ): T =
        ViewModelProvider(
            owner, BaseViewModelFactory(baseRepository)
        )[T::class.java]

    companion object {
        @Volatile
        private var instance: BaseViewModelProvider? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BaseViewModelProvider().also { instance = it }
            }
    }
}