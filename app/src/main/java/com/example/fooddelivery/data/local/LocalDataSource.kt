package com.example.fooddelivery.data.local

import androidx.room.FtsOptions
import com.example.fooddelivery.utils.room.LocalOrder
import com.example.fooddelivery.utils.room.OrderDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val sharedPrefManager: SharedPrefManager,
    val orderDao: OrderDao
    ) {

    fun saveToken(token: String) {
        sharedPrefManager.saveToken(token)
    }

    fun getToken(): String? {
        return sharedPrefManager.getToken()
    }

    fun listOrders(): List<LocalOrder> = orderDao.listOrders()

    fun addOrder(localOrder: LocalOrder) {
        orderDao.addOrder(localOrder)
    }

    fun removeOrder(localOrder: LocalOrder) {
        orderDao.removeOrder(localOrder)
    }
}