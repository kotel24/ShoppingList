package ru.mygames.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mygames.shoppinglist.R
import ru.mygames.shoppinglist.domain.ShopItem

//ShopListAdapter мы написали класс для отображения view with viewHolder
class ShopListAdapter:  ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {
    // передваваемый лист

    var onShopItemLongClickListener:((ShopItem)-> Unit)? = null
    var onShopItemClickListener:((ShopItem)-> Unit)? = null
    //как создавать вью
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_ENABLED->R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED->R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    //как вставить значение в вью
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener{
            onShopItemClickListener?.invoke(item)
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (item.enabled) return VIEW_TYPE_ENABLED
        else return VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15
    }
}