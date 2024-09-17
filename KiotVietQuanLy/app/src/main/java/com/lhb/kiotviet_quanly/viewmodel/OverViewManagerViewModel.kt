package com.lhb.kiotviet_quanly.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lhb.kiotviet_quanly.model.Bill
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
class OverViewManagerViewModel : ViewModel() {
    private val _bill = MutableLiveData<List<Bill>>(emptyList())
    val bills: LiveData<List<Bill>> = _bill

    private val database = FirebaseDatabase.getInstance()
    private val billRef = database.getReference("Bill")

    private val _searchResults = MutableLiveData<List<Bill>>(emptyList())
    val searchResults: LiveData<List<Bill>> = _searchResults

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        _isLoading.value = true
        billRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val billList = mutableListOf<Bill>()
                for (billSnapshot in snapshot.children) {
                    val bill = billSnapshot.getValue(Bill::class.java)
                    if (bill != null) {
                        billList.add(bill)
                    }
                }

                _bill.value = billList
                val today = LocalDate.now()
                filterBillsByMonthDay(today.dayOfMonth, today.monthValue, today.year)

                _isLoading.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                _isLoading.value = false
            }
        })
    }

    fun filterBillsByMonthDay(day: Int, month: Int, year: Int) {
        val allBills = _bill.value ?: emptyList()
        _searchResults.value = allBills.filter { bill ->
            val billDateStr = bill.date
            if (!billDateStr.isNullOrEmpty()) {
                try {
                    val billDate = LocalDate.parse(billDateStr, dateFormatter)
                    billDate.dayOfMonth == day && billDate.monthValue == month && billDate.year == year
                } catch (e: DateTimeParseException) {
                    Log.e("OverViewManagerViewModel", "Invalid date format for bill: $billDateStr")
                    false
                }
            } else {
                false
            }
        }
    }
}
