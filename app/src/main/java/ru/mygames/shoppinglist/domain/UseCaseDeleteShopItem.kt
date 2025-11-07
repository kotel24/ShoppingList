package ru.mygames.shoppinglist.domain

class UseCaseDeleteShopItem (private val shopListRepository: ShopListRepository){
    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}