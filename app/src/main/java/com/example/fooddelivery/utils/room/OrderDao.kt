package com.example.fooddelivery.utils.room

import androidx.room.*

@Dao
interface OrderDao {
    @Query("SELECT * FROM localorder")
    fun listOrders(): List<LocalOrder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrder(order: LocalOrder)

    @Query("SELECT * FROM localorder WHERE id=:orderID LIMIT 1")
    fun getOrderById(orderID: String): LocalOrder

    @Delete
    fun removeOrder(order: LocalOrder)

}