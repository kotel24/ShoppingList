package ru.mygames.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mygames.shoppinglist.domain.ShopItem
import ru.mygames.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private var shopList = mutableListOf<ShopItem>()

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID)
            shopItem.id = autoIncrementId++
        shopList.add(shopItem)
        updateShopList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateShopList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShowItem(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShowItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    private fun updateShopList() {
        shopListLiveData.postValue(shopList.toList())
    }

}