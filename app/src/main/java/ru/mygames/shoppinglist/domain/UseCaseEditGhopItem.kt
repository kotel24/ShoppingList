package ru.mygames.shoppinglist.domain

class UseCaseEditGhopItem (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}