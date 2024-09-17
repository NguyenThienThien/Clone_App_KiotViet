package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.model.RetailCustomer

@Composable
fun ItemRetailCustomer(
    retailCustomer: RetailCustomer,
    clickToSelectRetailCustomer: () -> Unit
){
    Row(
        modifier = Modifier
            .clickable { clickToSelectRetailCustomer() }
            .fillMaxWidth()
            .background(Color(0xffffffff))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = retailCustomer.imageUrl,
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
        Spacer(modifier = Modifier.padding(5.dp))
        Column {
            Text(text = retailCustomer.name!!, fontSize = 14.sp, fontWeight = FontWeight(500))
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = retailCustomer.phone!!, fontSize = 12.sp, fontWeight = FontWeight(400))
        }
    }
}
