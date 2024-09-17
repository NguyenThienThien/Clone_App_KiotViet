package com.lhb.kiotviet_quanly.view.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun ItemOrderDetail(
    product: Product,
    onCLick: () -> Unit,
    onItemSelectedNumber: (Int) -> Unit
) {

    var itemNumber by remember { mutableIntStateOf(1) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffffffff),
            contentColor = Color("#ffffff".toColorInt())
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                onCLick()
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
                    .size(50.dp)
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = product.name,
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = formatCurrency(product.price!!),
                        color = Color(0xff4169E1),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
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
        }
    }
}

