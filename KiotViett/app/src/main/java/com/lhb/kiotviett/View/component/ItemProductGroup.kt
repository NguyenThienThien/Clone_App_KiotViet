package com.lhb.kiotviett.View.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemProductGroup(onClick: () -> Unit){
    var selectedOption by remember { mutableStateOf("") }
    val listProductGroup = listOf(
        "BIA & THUỐC LÁ", "CLASSIC COCKTAILS", "MÓN KHAI VỊ", "SÚP", "TEA"
    )

    LazyColumn {
        items(listProductGroup.size){ index ->
            Row(
                modifier = Modifier
                    .clickable {
                        selectedOption = listProductGroup[index]
                    }
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedOption == listProductGroup[index],
                    onClick = { selectedOption = listProductGroup[index] }
                )

                Text(
                    text = listProductGroup[index],
                    color = Color(0xff303030),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                )
            }
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Divider()
            }
        }
    }
}