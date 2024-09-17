package com.lhb.kiotviett.View.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun ItemPayOnline(
    totalAmount: Int,
    title: String,
    isChecked: Boolean,
    onSelect: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color("#ffffff".toColorInt()).copy(alpha = 0.8f),
            contentColor = Color("#ffffff".toColorInt())
        ),
        modifier = Modifier
            .padding(start = 10.dp, top = 25.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onSelect() } // Update selected method on click
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isChecked, onCheckedChange = { onSelect() },
                    modifier = Modifier.background(Color.White)
                )
                Text(text = title, color = Color(0xff303030), fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Chọn tài khoản", color = Color(0xff303030), fontSize = 15.sp, fontWeight = FontWeight.Normal)
                IconButton(
                    onClick = { /* Handle account selection here */ }
                ) {
                    Icon(Icons.Default.ChevronRight, contentDescription = "", tint = Color(0xff303030), modifier = Modifier.size(24.dp))
                }
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = if (isChecked) formatCurrency(totalAmount) else "Nhập số tiền",
            color = if (isChecked) Color(0xff303030) else Color(0xffB6B6B6),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 30.dp, bottom = 10.dp)
        )
    }
}