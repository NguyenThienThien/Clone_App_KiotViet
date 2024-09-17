package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialogDiscount(
    originalPrice: Int,
    onDismiss: () -> Unit,
    onApplyDiscount: (Int) -> Unit
) {
    var discount by remember { mutableStateOf("") }
    var selectedDiscountType by remember { mutableStateOf("VNĐ") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(10.dp))
    ) {
        IconButton(
            onClick = { onDismiss() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
        ) {
            Icon(
                Icons.Outlined.Cancel,
                contentDescription = null,
                tint = Color(0xfffcfcfc),
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(50.dp))
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Giảm giá",
                fontSize = 22.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .padding(5.dp)
                        .padding(top = 10.dp)
                ) {
                    ToggleDiscount(
                        isSelected = { isSelected ->
                            selectedDiscountType = if (isSelected) "VNĐ" else "%"
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column {
                    CustomEnterTextUpdateOrderDetails(
                        value = discount,
                        onValueChange = { newValue -> discount = newValue },
                        placeholderText = "0",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            CustomButtonBlue(
                onClick = {
                    val discountValue = discount.toIntOrNull() ?: 0
                    val discountAmount = if (selectedDiscountType == "VNĐ") {
                        discountValue
                    } else {
                        (originalPrice * discountValue) / 100
                    }
                    onApplyDiscount(discountAmount)
                    onDismiss()
                },
                title = "Xong"
            )
        }
    }
}
