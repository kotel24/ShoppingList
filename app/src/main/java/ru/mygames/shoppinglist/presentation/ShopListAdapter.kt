package ru.mygames.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
            R.layout.item_shop_disabled,
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
        holder.itemView.setOnClickListener {
            //todo: open detail activity with item data
        }
        if (item.enabled){
            holder.tvName.text = item.name
            holder.tvCount.text = item.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_dark))
        }
    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.text = ""
        holder.tvCount.text = ""
        holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
    }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }


}