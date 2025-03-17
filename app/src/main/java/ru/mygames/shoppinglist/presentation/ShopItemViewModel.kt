package ru.mygames.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mygames.shoppinglist.data.ShopListRepositoryImpl
import ru.mygames.shoppinglist.domain.ShopItem
import ru.mygames.shoppinglist.domain.UseCaseAddShopItem
import ru.mygames.shoppinglist.domain.UseCaseEditShopItem
import ru.mygames.shoppinglist.domain.UseCaseGetShopItem

class ShopItemViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = UseCaseGetShopItem(repository)
    private val addShopItemUseCase = UseCaseAddShopItem(repository)
    private val editShopItemUseCase = UseCaseEditShopItem(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _getShopItem = MutableLiveData<ShopItem>()
    val getShopItem: LiveData<ShopItem>
        get() = _getShopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(itemId: Int){
        val item =  getShopItemUseCase.getShopItem(itemId)
        _getShopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)){
            addShopItemUseCase.addShopItem(ShopItem(name = name, count = count, enabled = true))
            closeScreen()
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)){
            _getShopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                closeScreen()
            }
        }

    }

    private fun parseName(inputName: String?):String{
        return inputName?.trim()?: ""
    }

    private fun parseCount(inputCount: String?):Int{
        return try {
            inputCount?.trim()?.toInt()?:0
        }catch (e:Exception) {
            0
        }
    }
    private fun validateInput(name:String, count:Int):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count<=0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }
    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
    fun closeScreen() {
        _shouldCloseScreen.value = Unit
    }
}