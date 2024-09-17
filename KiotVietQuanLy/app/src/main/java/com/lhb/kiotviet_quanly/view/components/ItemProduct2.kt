package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.model.Product
import com.lhb.kiotviet_quanly.utils.formatCurrency

@Composable
fun ItemProduct2(
    product: Product,
    isLoading: Boolean = false,
    onClickItem: () -> Unit
) {
    var isImageLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClickItem() }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color("#ffffff".toColorInt()).copy(alpha = 0.8f),
                contentColor = Color("#ffffff".toColorInt())
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(vertical = 3.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.image)
                            .crossfade(true) // Enable crossfade for smoother loading
                            .listener(
                                onSuccess = { _, _ -> isImageLoading = false },
                                onError = { _, _ -> isImageLoading = false }
                            )
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Show loading indicator if image is loading
                    if (isImageLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent, RoundedCornerShape(10.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color(0xff4169E1))
                        }
                    }
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier.weight(1f).padding(top = 5.dp)
                ) {
                    Text(
                        text = product.name,
                        color = Color(0xff303030),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = product.id,
                        color = Color(0xff606060),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.weight(1f).padding(top = 5.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = formatCurrency(product.price!!),
                        color = Color(0xff4169E1),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = product.inventory.toString(),
                        color = Color(0xff606060),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Show loading indicator if data is still loading
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000), RoundedCornerShape(10.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xff4169E1))
            }
        }
    }
}
