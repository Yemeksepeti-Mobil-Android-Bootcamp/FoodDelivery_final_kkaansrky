package com.example.fooddelivery.ui.updateuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.user.UserRequest
import com.example.fooddelivery.data.entity.user.UserResponse
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateUserViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    fun updateUser(userRequest: UserRequest): LiveData<Resource<UserResponse>> {
        return apiRepository.updateUser(userRequest)
    }
}