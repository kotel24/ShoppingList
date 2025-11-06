package ru.mygames.shoppinglist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {
    @Query("SELECT * FROM shopitems")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Query("SELECT * FROM shopitems WHERE id=:itemId LIMIT 1")
    fun getShopItem(itemId: Int): ShopItemDbModel

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shopitems WHERE id =:itemId")
    fun deleteShopItem(itemId: Int)
}