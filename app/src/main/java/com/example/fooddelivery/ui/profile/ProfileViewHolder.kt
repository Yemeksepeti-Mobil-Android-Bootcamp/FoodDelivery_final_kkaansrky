package com.example.fooddelivery.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.ApiRepository
import com.example.fooddelivery.data.entity.user.UserResponse
import com.example.fooddelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewHolder @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel(){
    fun getUser(): LiveData<Resource<UserResponse>> {
        return apiRepository.getUser()
    }

    fun logOutUser(){
        apiRepository.logOutUser()
    }
}