package com.lhb.kiotviett.View.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.lhb.kiotviett.Model.FoodDrink
import com.lhb.kiotviett.R

@Composable
fun ItemFoodDrink(
    foodDrink: FoodDrink,
    onClick: () -> Unit,
    onItemSelectedNumber: (Int) -> Unit,
) {
    var isSelected by remember { mutableStateOf(false) }
    var itemNumber by remember { mutableIntStateOf(1) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color("#ffffff".toColorInt()).copy(alpha = 0.8f),
            contentColor = Color("#ffffff".toColorInt())
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onClick()
                isSelected = !isSelected
            }
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = foodDrink.image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .aspectRatio(1 / 1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = foodDrink.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff303030),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = foodDrink.price.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff303030),
                    modifier = Modifier.fillMaxWidth()
                )
                if (isSelected) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        IconButton(
                            onClick = {
                                if (itemNumber > 1) {
                                    itemNumber -= 1
                                    onItemSelectedNumber(itemNumber)
                                } else {
                                    isSelected = !isSelected
                                }
                            }
                        ) {
                            Icon(
                                Icons.Outlined.Remove,
                                contentDescription = null,
                                tint = Color(0xff005595),
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(Color(0xff005595).copy(alpha = 0.2f)),
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = itemNumber.toString(),
                            fontSize = 16.sp,
                            color = Color(0xff303030)
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
                                tint = Color(0xff005595),
                                modifier = Modifier
                                    .size(26.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(Color(0xff005595).copy(alpha = 0.2f)),
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
    Divider()
}