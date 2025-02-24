package ru.mygames.shoppinglist.domain

class UseCaseGetShopItem (private val shopListRepository: ShopListRepository){
    fun getShowItem(shopItemId: Int): ShopItem{
        return shopListRepository.getShowItem(shopItemId)
    }
}