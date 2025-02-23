package ru.mygames.shoppinglist.domain

class UseCaseAddShopItem(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}