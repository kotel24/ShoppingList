package ru.mygames.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.mygames.shoppinglist.domain.ShopItem

class ShopListDiffCallback (
    //передаём старый и новый список элементов
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>,
): DiffUtil.Callback()
{   //возвращаем размер старого списка
    override fun getOldListSize(): Int {
        return oldList.size
    }
    //возвращаем размер нового списка
    override fun getNewListSize(): Int {
        return newList.size
    }
    //проверяем, с одинаковыми или разными эллементами мы работаем по их id
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    //проверяем, что содержимое эллементов не изменилось
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}
