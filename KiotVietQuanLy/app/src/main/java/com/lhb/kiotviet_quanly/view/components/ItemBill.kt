package com.lhb.kiotviet_quanly.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.model.Bill
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun ItemBill(
    bill: Bill
){
    val formattedAmount = formatCurrency(bill.totalAmount!!)
   Box(
       modifier = Modifier
           .fillMaxWidth()
           .background(Color.White)
           .padding(horizontal = 15.dp, vertical = 20.dp)
   ){
       Column(
           modifier = Modifier.fillMaxWidth()
       ) {
           Text(text = bill.date!!, color = Color(0xff505050), fontSize = 16.sp, fontWeight = FontWeight.Medium)
           Spacer(modifier = Modifier.height(10.dp))
           Row(
               modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
               Column {
                   Text(text = bill.customerName!!, color = Color(0xff303030), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                   Text(text = bill.date + " - " +bill.productId, color = Color(0xff505050), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                   Text(text = bill.productName + " x" +bill.quantity.toString(), color = Color(0xff505050), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                   Text(text = "+1 sản phẩm khác", color = Color(0xff505050), fontSize = 14.sp, fontWeight = FontWeight.Medium)
               }

               Column(
                   horizontalAlignment = Alignment.End
               ) {
                   Text(text = formattedAmount, color = Color(0xff303030), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                   Text(text = bill.paymentMrthod!!, color = Color(0xff505050), fontSize = 14.sp, fontWeight = FontWeight.Medium)
               }
           }
       }
   }
}