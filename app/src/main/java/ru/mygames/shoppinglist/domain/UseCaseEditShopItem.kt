package ru.mygames.shoppinglist.domain

class UseCaseEditShopItem (private val shopListRepository: ShopListRepository){
    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}