package ru.mygames.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import ru.mygames.shoppinglist.R
import ru.mygames.shoppinglist.domain.ShopItem

class ShopItemActivity:AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: TextInputLayout
    private lateinit var etCount: TextInputLayout
    private lateinit var buttonSave: Button
    private var screenMode = ""
    private var shopItemId = ShopItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initValues()
    }
//    private fun parseIntent(){
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
//            throw RuntimeException("Param screen mode is absent")
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD)
//            throw RuntimeException("Invalid screen mode: $mode")
//        if (mode == MODE_EDIT){
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID))
//                throw RuntimeException("Param shop item id is absent")
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
//        }
//    }
//    private fun initValues(){
//         tilName = findViewById(R.id.til_name)
//         tilCount = findViewById(R.id.til_count)
//         etName = findViewById(R.id.et_name)
//         etCount = findViewById(R.id.et_count)
//         buttonSave = findViewById(R.id.save_button)
//    }



    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_screen_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context,shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}