package ru.mygames.shoppinglist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mygames.shoppinglist.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var shopItemContainer:FragmentContainerView? = null
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            shopListAdapter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            if (!isOnePaneMode()) {
                val fragment = ShopItemFragment.newInstanceAddItem()
                launchFragment(fragment)
            }else {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            }
        }

    }

//    override fun onEditingFinished() {
//        Toast.makeText(this@MainActivity,"Success" ,Toast.LENGTH_SHORT).show()
//        supportFragmentManager.popBackStack()
//    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }
    private fun launchFragment(fragment: Fragment){
        supportFragmentManager
               .beginTransaction()
               .replace(R.id.shop_item_container, fragment)
               .commit()
    }
    private fun setupRecyclerView() {
        val rvShoplist = findViewById<RecyclerView>(R.id.recyclerView)
        shopListAdapter = ShopListAdapter()
        with(rvShoplist) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupOnLongClickListener()
        setupOnClickLictener()
        setupSwipeListener(rvShoplist)
    }

    private fun setupSwipeListener(rvShoplist: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShoplist)
    }

    private fun setupOnClickLictener() {
        shopListAdapter.onShopItemClickListener = {
            if (!isOnePaneMode()) {
                val fragment = ShopItemFragment.newInstanceEditItem(it.id)
                launchFragment(fragment)
            }else {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            }

        }
    }

    private fun setupOnLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.editShopItem(it)
        }
    }
}