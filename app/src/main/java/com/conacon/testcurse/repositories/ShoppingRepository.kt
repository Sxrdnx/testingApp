package com.conacon.testcurse.repositories

import androidx.lifecycle.LiveData
import com.conacon.testcurse.data.local.ShoppingItem
import com.conacon.testcurse.data.remote.responses.ImageResponse
import com.conacon.testcurse.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}