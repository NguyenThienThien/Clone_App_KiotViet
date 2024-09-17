package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.ui.theme.OpenSans

@Composable
fun BottomSheetPhoneNumber(
    onClickCancel: () -> Unit
){
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(10.dp)
    ) {
        Button(
            onClick = { onClickCancel() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff3d3f43)
            ),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = null,
                    tint = Color(0xff6a6a6a),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Gọi 1900 6522",
                    fontSize = 16.sp,
                    color = Color(0xff0081ff),
                    fontFamily = OpenSans,
                    fontWeight = FontWeight(500)
                )
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Button(
            onClick = { onClickCancel() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff2c2c2e)
            ),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Text(
                text = "Hủy",
                fontSize = 18.sp,
                color = Color(0xff0081ff),
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
@Preview
fun PreviewBottomSheetPhoneNumber() {
    BottomSheetPhoneNumber(onClickCancel = {})
}