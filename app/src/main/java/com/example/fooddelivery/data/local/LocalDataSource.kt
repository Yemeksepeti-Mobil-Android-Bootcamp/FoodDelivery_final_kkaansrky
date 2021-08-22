package com.example.fooddelivery.data.local

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

    fun listLocalOrders(): List<LocalOrder> = orderDao.listOrders()

    fun addLocalOrder(localOrder: LocalOrder) {
        orderDao.addOrder(localOrder)
    }

    fun removeLocalOrder(localOrderId : String) {
        orderDao.removeOrder(localOrderId)
    }

    fun getLocalOrderById(): LocalOrder {
       return orderDao.getOrderById("1")
    }
}