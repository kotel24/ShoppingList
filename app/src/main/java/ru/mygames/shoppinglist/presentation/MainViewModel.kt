package ru.mygames.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mygames.shoppinglist.data.ShopListRepositoryImpl
import ru.mygames.shoppinglist.domain.ShopItem
import ru.mygames.shoppinglist.domain.UseCaseDeleteShopItem
import ru.mygames.shoppinglist.domain.UseCaseEditGhopItem
import ru.mygames.shoppinglist.domain.UseCaseGetShopList

class MainViewModel:ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val getShopList = UseCaseGetShopList(repository)
    private val deleteShopItem = UseCaseDeleteShopItem(repository)
    private val editGhopItem = UseCaseEditGhopItem(repository)

    val shopList = getShopList.getShopList()


    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItem.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editGhopItem.editShopItem(newItem)
    }
}