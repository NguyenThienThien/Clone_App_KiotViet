package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.ui.theme.ColorTextGray
import com.lhb.kiotviet_quanly.ui.theme.ColorWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextEnter(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(ColorWhite)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White),
            label = {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                )
            },
            textStyle = TextStyle(
                color = Color(0xff303030),
                fontWeight = FontWeight(400),
                fontSize = 16.sp
            ),
            singleLine = true,
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Close, contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xff4e4c58)
                        )
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(0xffb6b6b6),
                focusedBorderColor = Color(0xff005595),
                unfocusedLabelColor = Color(0xffb6b6b6),
                focusedLabelColor = Color(0xff005595),
            ),
            shape = RoundedCornerShape(11.dp),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextSelected(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White)
                .border(
                    BorderStroke(1.dp, Color(0xffb6b6b6)), // Border similar to OutlinedTextField
                    shape = RoundedCornerShape(11.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(11.dp),
            color = Color.Transparent,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = ColorTextGray
                )
                Icon(
                    Icons.Outlined.ArrowForwardIos,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = ColorTextGray
                )
            }
        }
    }
}
