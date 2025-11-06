package ru.mygames.shoppinglist.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShopItems")
data class ShopItemDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
)