package ru.mygames.shoppinglist.domain

import androidx.lifecycle.LiveData

class UseCaseGetShopList(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}