package com.lhb.kiotviet_quanly.view.components

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun InformationProduct(product: Product){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xfffffff),
            contentColor = Color("#ffffff".toColorInt())
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {

            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = product.name,
                    color = Color(0xff303030),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.id,
                            color = Color(0xff505050),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "0",
                            color = Color(0xff505050),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(0xffFFA500).copy(alpha = 0.3f))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "KH đặt : 0",
                        color = Color(0xff505050),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xff808080).copy(alpha = 0.2f))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = formatCurrency(product.price!!) ,
                        color = Color(0xff4169E1),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}

@Composable
fun ContentFirst(
    initialQuantity: Int,
    onItemSelectedNumber: (Int) -> Unit,
){
    var itemNumber by remember { mutableIntStateOf(initialQuantity) }
    Spacer(modifier = Modifier.padding(10.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Số lượng", fontSize = 18.sp, fontWeight = FontWeight(500))
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xffd2d4da),
                    shape = RoundedCornerShape(10.dp),
                )
                .height(35.dp)
        ) {
            IconButton(
                onClick = {
                    if (itemNumber > 1) {
                        itemNumber -= 1
                        onItemSelectedNumber(itemNumber)
                    }
                }
            ) {
                Icon(
                    Icons.Outlined.Remove,
                    contentDescription = null,
                    tint = Color(0xff959ba2),
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = itemNumber.toString(),
                fontSize = 18.sp,
                color = Color(0xff303030),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {
                    itemNumber += 1
                    onItemSelectedNumber(itemNumber)
                },
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = null,
                    tint = Color(0xff959ba2),
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
            }
        }
    }
}

@Composable
fun ContentSecond(
    title: String,
    price: Int,
    onApplyDiscount: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.padding(15.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight(500))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = formatCurrency(price), fontSize = 18.sp, fontWeight = FontWeight(500), modifier = Modifier.clickable {
            showDialog = true
        })

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                CustomDialogDiscount(
                    originalPrice = price,
                    onDismiss = { showDialog = false },
                    onApplyDiscount = { discountAmount ->
                        onApplyDiscount(discountAmount)
                        showDialog = false
                    }
                )
            }
        }
    }
}


@Composable
fun CustomDivider(){
    Spacer(modifier = Modifier.padding(5.dp))
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.width(150.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Divider()
        }
    }
}

@Composable
fun CustomEnterTextUpdateOrderDetails(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            androidx.compose.material.Text(
                text = placeholderText,
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End, fontSize = 24.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if(value.isNotEmpty()){
                IconButton(onClick = {onValueChange("")}) {
                    Icon(
                        Icons.Outlined.Cancel,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    )
}