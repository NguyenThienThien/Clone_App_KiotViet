package com.lhb.kiotviet_quanly.viewmodel

import android.net.Uri
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
import com.google.firebase.storage.FirebaseStorage
import com.lhb.kiotviet_quanly.model.RetailCustomer
import kotlinx.coroutines.launch
import java.util.UUID

class RetailCustomerViewModel : ViewModel() {
    var username by mutableStateOf("")
    var phone by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isCustomerAdded by mutableStateOf(false)

    private val _retailCustomer = MutableLiveData<List<RetailCustomer>>(emptyList())
    val retailCustomers: LiveData<List<RetailCustomer>> = _retailCustomer

    private val database = FirebaseDatabase.getInstance()
    private val retailCustomerRef = database.getReference("RetailCustomer")

    // Regular expression to validate phone number
    private val phoneRegex = Regex("^[0-9]{10,11}$") // Valid for 10-11 digits

    fun onImageSelected(uri: Uri?) {
        imageUri = uri
    }

    fun onUsernameChanged(newUsername: String) {
        username = newUsername
    }

    fun onPhoneChanged(newPhone: String) {
        phone = newPhone
    }

    fun addCustomer() {
        // Validate input fields
        if (username.isBlank()) {
            errorMessage = "Tên khách hàng không được để trống!"
            return
        }

        if (phone.isBlank()) {
            errorMessage = "Số điện thoại không được để trống!"
            return
        }

        if (!phone.matches(phoneRegex)) {
            errorMessage = "Số điện thoại không hợp lệ! Vui lòng nhập đúng định dạng."
            return
        }

        if (imageUri == null) {
            errorMessage = "Chưa chọn ảnh!"
            return
        }

        isLoading = true
        viewModelScope.launch {
            try {
                val customerId = UUID.randomUUID()
                val storageReference = FirebaseStorage.getInstance().reference
                    .child("RetailCustomer/$customerId.jpg")
                val uploadTask = storageReference.putFile(imageUri!!)
                uploadTask.addOnSuccessListener {
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        val customer = RetailCustomer(
                            id = customerId.toString(),
                            name = username,
                            phone = phone,
                            imageUrl = uri.toString()
                        )
                        FirebaseDatabase.getInstance().reference
                            .child("RetailCustomer")
                            .push()
                            .setValue(customer)
                            .addOnSuccessListener {
                                isLoading = false
                                isCustomerAdded = true
                                errorMessage = null // Clear error message after success
                            }
                            .addOnFailureListener {
                                errorMessage = "Lỗi khi thêm khách hàng!"
                                isLoading = false
                            }
                    }.addOnFailureListener {
                        errorMessage = "Lỗi khi lấy URL ảnh!"
                        isLoading = false
                    }
                }.addOnFailureListener {
                    errorMessage = "Lỗi khi tải ảnh lên!"
                    isLoading = false
                }
            } catch (e: Exception) {
                errorMessage = e.message
                isLoading = false
            }
        }
    }

    init {
        fetchRetailCustomer()
    }

    private fun fetchRetailCustomer() {
        viewModelScope.launch {
            retailCustomerRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val retailCustomerList = mutableListOf<RetailCustomer>()
                    for (retailCustomerSnapshot in snapshot.children) {
                        val retailCustomer = retailCustomerSnapshot.getValue(RetailCustomer::class.java)
                        if (retailCustomer != null) {
                            retailCustomerList.add(retailCustomer)
                        }
                    }
                    _retailCustomer.value = retailCustomerList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}
