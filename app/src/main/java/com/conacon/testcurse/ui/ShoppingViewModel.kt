package com.conacon.testcurse.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conacon.testcurse.data.local.ShoppingItem
import com.conacon.testcurse.data.remote.responses.ImageResponse
import com.conacon.testcurse.other.Constants
import com.conacon.testcurse.other.Event
import com.conacon.testcurse.other.Resource
import com.conacon.testcurse.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
): ViewModel() {
    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem)=viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }


    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem)= viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            _insertShoppingItemStatus.postValue(Event(Resource.error( null,"The fields must not be empty",)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(Resource.error(null,"The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters")))
            return
        }
        if(priceString.length > Constants.MAX_PRICE_LENGTH) {
            _insertShoppingItemStatus.postValue(Event(Resource.error(null,"El precio del item" +
                    "no debe sobrepasar los ${Constants.MAX_PRICE_LENGTH} characters")))
            return
        }
        val amount = try {
            amountString.toInt()
        } catch(e: Exception) {
            _insertShoppingItemStatus.postValue(Event(Resource.error(null,"Ingrese una cantidad valida")))
            return
        }
        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }

    fun searchForImage(imageQuery: String) {
        if(imageQuery.isEmpty()) {
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }

}