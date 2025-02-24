package ru.mygames.shoppinglist.domain

class UseCaseDeleteShopItem (private val shopListRepository: ShopListRepository){
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}