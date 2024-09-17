package com.lhb.kiotviet_quanly.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lhb.kiotviet_quanly.model.ProductType
import kotlinx.coroutines.launch

class ProductTypeViewModel : ViewModel(){
    private val _productType = MutableLiveData<List<ProductType>>(emptyList())
    val productType: LiveData<List<ProductType>> = _productType

    private val database = FirebaseDatabase.getInstance()
    private val productTypeRef = database.getReference("ProductType")

    init {
        fetchProductTypes()
    }

    private fun fetchProductTypes(){
        viewModelScope.launch {
            productTypeRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val productTypeList = mutableListOf<ProductType>()
                    for(productTypeSnapshot in snapshot.children){
                        val productType = productTypeSnapshot.getValue(ProductType::class.java)
                        if(productType!= null){
                            productTypeList.add(productType)
                        }
                    }
                    _productType.value = productTypeList
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("ProductTypeError", "ErrorProductType: $error")
                }
            })
        }
    }

}