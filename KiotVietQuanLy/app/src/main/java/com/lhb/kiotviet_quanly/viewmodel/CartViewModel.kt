package com.lhb.kiotviet_quanly.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lhb.kiotviet_quanly.model.Cart
import com.lhb.kiotviet_quanly.model.Item
import com.lhb.kiotviet_quanly.model.Product
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel : ViewModel() {
    private val _cart = MutableLiveData<List<Cart>>(emptyList())
    val carts: LiveData<List<Cart>> = _cart

    private val database = FirebaseDatabase.getInstance().reference
    private val cartRef = database.child("Cart")

    var isCartAdded by mutableStateOf(false)
    private var errorMessage by mutableStateOf<String?>(null)

    init {
        fetchCart()
    }

    private fun fetchCart(){
        viewModelScope.launch {
            cartRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val cartList = mutableListOf<Cart>()
                    for(cartSnapshot in snapshot.children){
                        val cart = cartSnapshot.getValue(Cart::class.java)
                        if(cart != null){
                            cartList.add(cart)
                        }
                    }
                    _cart.value = cartList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    fun addCart(
        customerName: String,
        totalAmount: Int,
        selectedProducts: List<Product>,
        quantities: List<Int>
    ) {
        viewModelScope.launch {
            val items = selectedProducts.zip(quantities).map { (product, quantity) ->
                Item(
                    id = UUID.randomUUID().toString(),
                    productId = product.id,
                    productName = product.name,
                    quantity = quantity,
                    price = product.price,
                    image = product.image
                )
            }

            val cartId = UUID.randomUUID().toString()

            val cart = Cart(
                id = cartId,
                customerName = customerName,
                totalAmount = totalAmount,
                cartStatus = "active",
                items = items
            )

            cartRef.child(cartId).setValue(cart)
                .addOnSuccessListener {
                    isCartAdded = true
                }
                .addOnFailureListener { error ->
                    errorMessage = "Failed to add cart: ${error.message}"
                }
        }
    }
}
