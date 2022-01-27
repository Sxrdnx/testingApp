package com.conacon.testcurse.repositories

import androidx.lifecycle.LiveData
import com.conacon.testcurse.data.local.ShoppinDao
import com.conacon.testcurse.data.local.ShoppingItem
import com.conacon.testcurse.data.remote.PixaBayAPI
import com.conacon.testcurse.data.remote.responses.ImageResponse
import com.conacon.testcurse.other.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao:ShoppinDao,
    private val pixaBayAPI: PixaBayAPI
): ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return  try {
            val response = pixaBayAPI.searchForImage(imageQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error(null, "error inesperado ocurrio")
            }else{
                Resource.error(null, "error inesperado ocurrio")
            }
        }catch (e: Exception){
            Resource.error(null, "error inesperado ocurrio")
        }
    }

}