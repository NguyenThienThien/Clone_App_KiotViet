import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lhb.kiotviet_quanly.model.Bill
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
class BillViewModel : ViewModel() {
    private val _bill = MutableLiveData<List<Bill>>(emptyList())
    val bills: LiveData<List<Bill>> = _bill

    private val _searchResults = MutableLiveData<List<Bill>>(emptyList())
    val searchResults: LiveData<List<Bill>> = _searchResults
    private var searchByName: String = ""

    private val database = FirebaseDatabase.getInstance()
    private val billRef = database.getReference("Bill")

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    init {
        fetchBills()
    }

    private fun fetchBills() {
        _isLoading.value = true
        billRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val billList = mutableListOf<Bill>()
                for (billSnapshot in snapshot.children) {
                    val bill = billSnapshot.getValue(Bill::class.java)
                    if (bill != null) {
                        Log.d("BillDate", "Date: ${bill.date}")
                        billList.add(bill)
                    }
                }
                _bill.value = billList
                filterBillsByMonth(LocalDate.now().monthValue, LocalDate.now().year)
                _isLoading.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                _isLoading.value = false
            }
        })
    }

    fun filterBillsByMonth(month: Int, year: Int) {
        val allBills = _bill.value ?: emptyList()
        _searchResults.value = allBills.filter { bill ->
            val billDateStr = bill.date
            if (!billDateStr.isNullOrEmpty()) {
                try {
                    val billDate = LocalDate.parse(billDateStr, dateFormatter)
                    val isMatching = billDate.monthValue == month && billDate.year == year
                    isMatching
                } catch (e: DateTimeParseException) {
                    Log.e("DateParseError", "Error parsing date: $billDateStr with exception: ${e.message}")
                    false
                }
            } else {
                false
            }
        }
    }

    fun searchBills(query: String) {
        viewModelScope.launch {
            val allBills = _bill.value ?: emptyList()
            val normalizedQuery = query.removeDiacritics()
            val filteredBills = if (query.isNotEmpty()) {
                allBills.filter { bill ->
                    val normalizedCustomerName = bill.customerName?.removeDiacritics() ?: ""
                    normalizedCustomerName.contains(normalizedQuery)
                }
            } else {
                allBills
            }
            _searchResults.value = filteredBills
        }
    }

    private fun String.removeDiacritics(): String {
        val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
        return normalized.replace(Regex("\\p{Mn}"), "").toLowerCase()
    }
}
