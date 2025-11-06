package ru.mygames.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mygames.shoppinglist.data.mapper.toDbModel
import ru.mygames.shoppinglist.data.mapper.toEntity
import ru.mygames.shoppinglist.data.room.AppDatabase
import ru.mygames.shoppinglist.domain.ShopItem
import ru.mygames.shoppinglist.domain.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(
    application: Application
): ShopListRepository {
    private var shopListDao = AppDatabase.getInstance(application).shopListDao()
    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopItem.toDbModel())
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListDao.getShopList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopItem.toDbModel())
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopListDao.getShopItem(shopItemId).toEntity()
    }

}