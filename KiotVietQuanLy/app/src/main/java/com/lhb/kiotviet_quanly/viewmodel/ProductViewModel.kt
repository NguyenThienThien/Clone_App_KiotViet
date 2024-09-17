package com.lhb.kiotviet_quanly.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.lhb.kiotviet_quanly.model.Product
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

class ProductViewModel : ViewModel() {
    // Danh sách sản phẩm gốc (để lọc)
    private val originalProductList = mutableListOf<Product>()

    // LiveData cho danh sách sản phẩm
    private val _product = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _product

    // LiveData cho loại sản phẩm được chọn
    private val _selectedProductType = MutableLiveData<String?>()
    val selectedProductType: LiveData<String?> = _selectedProductType

    // LiveData cho trạng thái tải
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData cho thông báo lỗi
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // LiveData cho trạng thái thành công khi thêm sản phẩm
    private val _addProductSuccess = MutableLiveData<Boolean>()
    val addProductSuccess: LiveData<Boolean> = _addProductSuccess

    // Tham chiếu đến Firebase Database
    private val database = FirebaseDatabase.getInstance()
    private val productRef = database.getReference("Product")

    // Trạng thái sắp xếp sản phẩm theo giá
    var isAscending = true

    // Trạng thái sắp xếp sản phẩm theo tên
    var isNameAscending = true

    // Khởi tạo để tải danh sách sản phẩm
    init {
        fetchProducts()
    }

    // Phương thức lấy danh sách sản phẩm từ Firebase
    private fun fetchProducts() {
        _isLoading.value = true
        viewModelScope.launch {
            productRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val productList = mutableListOf<Product>()
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        if (product != null) {
                            productList.add(product)
                        }
                    }
                    originalProductList.clear()
                    originalProductList.addAll(productList)
                    _product.value = productList
                    _isLoading.value = false
                }

                override fun onCancelled(error: DatabaseError) {
                    _error.value = "Lỗi khi tải sản phẩm: ${error.message}"
                    _isLoading.value = false
                }
            })
        }
    }

    // Phương thức sắp xếp sản phẩm theo tên
    fun sortProductsByName() {
        val sortedList = _product.value?.sortedWith(compareBy {
            val normalizedProductName = removeAccents(it.name.trim().lowercase())
            normalizedProductName
        }) ?: emptyList()

        _product.value = if (isNameAscending) {
            sortedList // Từ A -> Z
        } else {
            sortedList.reversed() // Từ Z -> A
        }

        isNameAscending = !isNameAscending
    }

    // Phương thức chọn loại sản phẩm và lọc danh sách
    fun setSelectedProductType(productType: String?) {
        _selectedProductType.value = productType
        filterProductsByType(productType)
    }

    // Phương thức lọc sản phẩm theo loại
    private fun filterProductsByType(productType: String?) {
        _product.value = if (productType.isNullOrEmpty() || productType == "Tất cả loại hàng") {
            originalProductList
        } else {
            originalProductList.filter { it.productType == productType }
        }
    }

    // Phương thức sắp xếp sản phẩm theo giá
    fun sortProductsByPrice() {
        val sortedList = _product.value?.sortedWith(compareBy {
            if (isAscending) it.price else -it.price!!
        }) ?: emptyList()
        _product.value = sortedList
        isAscending = !isAscending
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    fun searchProductsByName(searchQuery: String) {
        val normalizedQuery = removeAccents(searchQuery.trim().lowercase())

        _product.value = originalProductList.filter { product ->
            val normalizedProductName = removeAccents(product.name.trim().lowercase())
            normalizedProductName.contains(normalizedQuery)
        }
    }

    // Hàm loại bỏ dấu tiếng Việt
    private fun removeAccents(input: String): String {
        val normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized)
            .replaceAll("")
    }

    // Phương thức thêm sản phẩm mới
    fun addProduct(
        imageProduct: Uri?,
        nameProduct: String,
        quantity: Int,
        originalPrice: Int,
        sellingPrice: Int,
        productType: String
    ) {
        val randomId = (1..99).random().toString().padStart(2, '0')
        val productId = "$productType$randomId"

        if (imageProduct == null) {
            _error.value = "Vui lòng chọn ảnh"
            return
        }

        if (nameProduct.isEmpty()) {
            _error.value = "Vui lòng nhập tên sản phẩm"
            return
        }

        if (quantity <= 0) {
            _error.value = "Số lượng phải lớn hơn 0"
            return
        }

        if (originalPrice <= 0) {
            _error.value = "Giá gốc phải lớn hơn 0"
            return
        }

        if (sellingPrice <= 0) {
            _error.value = "Giá bán phải lớn hơn 0"
            return
        }

        viewModelScope.launch {
            try {
                val storageReference =
                    FirebaseStorage.getInstance().reference.child("Products/$productId.jpg")
                val uploadTask = storageReference.putFile(imageProduct!!)
                uploadTask.addOnSuccessListener {
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        val newProduct = Product(
                            productId, nameProduct, sellingPrice, uri.toString(), quantity,
                            quantity, originalPrice, quantity, 0, productType
                        )
                        productRef.child(productId).setValue(newProduct).addOnSuccessListener {
                            _error.value = "Thêm sản phẩm thành công"
                            _addProductSuccess.value = true
                        }.addOnFailureListener {
                            _error.value = "Không thể thêm sản phẩm: ${it.message}"
                        }
                    }
                }.addOnFailureListener {
                    _error.value = "Lỗi tải ảnh: ${it.message}"
                }
            } catch (e: Exception) {
                _error.value = "Lỗi: ${e.message}"
            }
        }
    }

    fun updateProductInDatabase(
        productId: String,
        nameProduct: String,
        quantity: Int,
        originalPrice: Int,
        sellingPrice: Int,
        productType: String,
        imageProduct: Uri?, // Ảnh mới (nếu có), hoặc null nếu không cập nhật ảnh
        currentImageUrl: String // URL của ảnh hiện tại
    ) {
        val TAG = "UpdateProduct"

        if (nameProduct.isEmpty()) {
            _error.value = "Vui lòng nhập tên sản phẩm"
            Log.d(TAG, "Lỗi: Tên sản phẩm rỗng.")
            return
        }

        if (quantity <= 0) {
            _error.value = "Số lượng phải lớn hơn 0"
            Log.d(TAG, "Lỗi: Số lượng <= 0.")
            return
        }

        if (originalPrice <= 0) {
            _error.value = "Giá gốc phải lớn hơn 0"
            Log.d(TAG, "Lỗi: Giá gốc <= 0.")
            return
        }

        if (sellingPrice <= 0) {
            _error.value = "Giá bán phải lớn hơn 0"
            Log.d(TAG, "Lỗi: Giá bán <= 0.")
            return
        }

        viewModelScope.launch {
            if (imageProduct == null) {
                // Nếu không cập nhật ảnh, giữ URL cũ
                val updatedProduct = Product(
                    productId, nameProduct, sellingPrice, currentImageUrl, quantity, quantity, originalPrice, quantity, 0, productType)
                productRef.child(productId).setValue(updatedProduct).addOnSuccessListener {
                    _error.value = "Cập nhật sản phẩm thành công"
                    _addProductSuccess.value = true
                }.addOnFailureListener {
                    _error.value = "Không thể cập nhật sản phẩm: ${it.message}"
                }
            } else {
                // Nếu cập nhật ảnh, upload ảnh mới lên Firebase Storage
                val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                val dateString = dateFormat.format(Date())
                try {
                    val storageReference =
                        FirebaseStorage.getInstance().reference.child("Products/${productId}_$dateString.jpg")
                    val uploadTask = storageReference.putFile(imageProduct)
                    uploadTask.addOnSuccessListener {
                        storageReference.downloadUrl.addOnSuccessListener { newImageUrl ->
                            val updatedProduct = Product( productId, nameProduct, sellingPrice, newImageUrl.toString(), quantity, quantity, originalPrice, quantity, 0, productType)
                            productRef.child(productId).setValue(updatedProduct)
                                .addOnSuccessListener {
                                    _error.value = "Cập nhật sản phẩm thành công"
                                    _addProductSuccess.value = true
                                }.addOnFailureListener {
                                _error.value = "Không thể cập nhật sản phẩm: ${it.message}"
                            }
                        }
                    }.addOnFailureListener {
                        _error.value = "Lỗi tải ảnh: ${it.message}"
                    }
                } catch (e: Exception) {
                    _error.value = "Lỗi: ${e.message}"
                }
            }
        }
    }

    // Phương thức xóa lỗi
    fun clearError() {
        _error.value = null
    }

    // Phương thức xóa trạng thái thành công
    fun clearAddProductSuccess() {
        _addProductSuccess.value = false
    }
}
