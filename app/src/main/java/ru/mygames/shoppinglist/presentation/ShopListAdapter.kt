package ru.mygames.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mygames.shoppinglist.R
import ru.mygames.shoppinglist.domain.ShopItem
//ShopListAdapter мы написали класс для отображения view with viewHolder
class ShopListAdapter:  RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder> () {
    // передваваемый лист
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged() //вызываем метод для перерисовки вью
        }
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

    // количество элементов в коллекции
    override fun getItemCount(): Int {
        return shopList.size
    }

    //как вставить значение в вью
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener{
            onShopItemClickListener?.invoke(item)
        }
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
    }


    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        if (item.enabled) return VIEW_TYPE_ENABLED
        else return VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 15
}
}