package com.lhb.kiotviet_quanly.view.navigation

sealed class BottomBarScreens(val screen: String){
   data object OverViewManager : BottomBarScreens("OverViewManager")
   data object BillManager : BottomBarScreens("BillManager")
   data object SellManager : BottomBarScreens("sellManager")
   data object ProductManager : BottomBarScreens("productManager")
   data object OtherManager : BottomBarScreens("otherManager")
}