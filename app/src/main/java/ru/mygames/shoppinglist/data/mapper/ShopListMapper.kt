package ru.mygames.shoppinglist.data.mapper

import ru.mygames.shoppinglist.data.room.ShopItemDbModel
import ru.mygames.shoppinglist.domain.ShopItem

fun ShopItem.toDbModel(): ShopItemDbModel = ShopItemDbModel(
    id = id,
    name = name,
    count = count,
    enabled = enabled
)
fun ShopItemDbModel.toEntity(): ShopItem = ShopItem(
    id = id,
    name = name,
    count = count,
    enabled = enabled
)
fun List<ShopItemDbModel>.toEntity(): List<ShopItem> = map {
    it.toEntity()
}