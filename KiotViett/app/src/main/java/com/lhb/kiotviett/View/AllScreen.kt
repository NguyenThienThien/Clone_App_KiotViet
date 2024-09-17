package com.example.kiotviet.avtivities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kiotviet.avtivities.component.ItemTypes
import com.example.kiotviet.avtivities.component.RoomItem
import com.example.kiotviet.models.Category
import com.example.kiotviet.models.Room
import com.lhb.kiotviett.View.navigator.ScreenNames

@Composable
fun AllScreen(navController: NavController){

    var selectedIndex by remember { mutableStateOf<Category?>(null) }

    val listRoom = listOf(
        Room(id = "1", nameRoom = "Phòng VIP 1", category = "1"),
        Room(id = "1", nameRoom = "Phòng VIP 2", category = "1"),
        Room(id = "1", nameRoom = "Phòng VIP 3", category = "1"),
        Room(id = "1", nameRoom = "Phòng VIP 4", category = "1"),
        Room(id = "1", nameRoom = "Phòng VIP 5", category = "1"),
        Room(id = "1", nameRoom = "Bàn 1", category = "2"),
        Room(id = "1", nameRoom = "Bàn 2", category = "2"),
        Room(id = "1", nameRoom = "Bàn 3", category = "2"),
        Room(id = "1", nameRoom = "Bàn 4", category = "2"),
        Room(id = "1", nameRoom = "Bàn 5", category = "2"),
        Room(id = "1", nameRoom = "Bàn 1", category = "3"),
        Room(id = "1", nameRoom = "Bàn 2", category = "3"),
        Room(id = "1", nameRoom = "Bàn 3", category = "3"),
        Room(id = "1", nameRoom = "Bàn 4", category = "3"),
        Room(id = "1", nameRoom = "Bàn 5", category = "3"),
    )

    val listCategory = listOf(
        Category(id = "1", name = "Phòng vip"),
        Category(id = "2", name = "Lầu 1"),
        Category(id = "3", name = "lầu 2"),
    )

    val filteredRoom = if (selectedIndex != null) {
        listRoom.filter { it.category == selectedIndex?.id }
    } else {
        listRoom
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf4f6f8))
            .padding(10.dp),
    ) {

        Spacer(modifier = Modifier.padding(2.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(listCategory) { category ->
                ItemTypes(
                    category = category,
                    isSelected = category == selectedIndex,
                    onClick = {
                        selectedIndex = category
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.padding(4.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(filteredRoom.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { item ->
                        RoomItem(
                            room = item,
                            modifier = Modifier.weight(1f),
                            onClick = {navController.navigate(ScreenNames.FoodAndDrinkScreen.route)}
                        )
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}