package ru.mygames.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.mygames.shoppinglist.data.ShopListRepositoryImpl
import ru.mygames.shoppinglist.domain.ShopItem
import ru.mygames.shoppinglist.domain.UseCaseDeleteShopItem
import ru.mygames.shoppinglist.domain.UseCaseEditShopItem
import ru.mygames.shoppinglist.domain.UseCaseGetShopList

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)
    private val getShopList = UseCaseGetShopList(repository)
    private val deleteShopItem = UseCaseDeleteShopItem(repository)
    private val editGhopItem = UseCaseEditShopItem(repository)

    val shopList = getShopList.getShopList()


    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            deleteShopItem.deleteShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editGhopItem.editShopItem(newItem)
        }
    }

}