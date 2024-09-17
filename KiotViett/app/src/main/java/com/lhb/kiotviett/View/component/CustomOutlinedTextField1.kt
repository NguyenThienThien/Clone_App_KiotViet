package com.lhb.kiotviett.View.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField1(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(45.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xffE8E8E8)),
        textStyle = TextStyle(
            color = Color(0xff303030),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    onValueChange("")
                }
            ) {
                Icon(
                    Icons.Outlined.Close, contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Gray)
                        .padding(3.dp),
                    tint = Color(0xffFFFFFF)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )

}