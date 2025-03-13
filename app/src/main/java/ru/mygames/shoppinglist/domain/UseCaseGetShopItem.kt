package ru.mygames.shoppinglist.domain

class UseCaseGetShopItem (private val shopListRepository: ShopListRepository){
    fun getShopItem(shopItemId: Int): ShopItem{
        return shopListRepository.getShopItem(shopItemId)
    }
}