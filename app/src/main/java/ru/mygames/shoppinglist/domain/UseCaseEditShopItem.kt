package ru.mygames.shoppinglist.domain

class UseCaseEditShopItem (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}