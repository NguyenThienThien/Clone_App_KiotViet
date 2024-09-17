package com.lhb.kiotviet_quanly.view.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lhb.kiotviet_quanly.view.AddDescriptionScreen
import com.lhb.kiotviet_quanly.view.AddProductScreen
import com.lhb.kiotviet_quanly.view.AddressScreen
import com.lhb.kiotviet_quanly.view.CancelExportScreen
import com.lhb.kiotviet_quanly.view.CheckInventoryScreen
import com.lhb.kiotviet_quanly.view.ForgotPassword
import com.lhb.kiotviet_quanly.view.InventoryLevels
import com.lhb.kiotviet_quanly.view.NewAddressScreen
import com.lhb.kiotviet_quanly.view.NewTradeMarkScreen
import com.lhb.kiotviet_quanly.view.OnBoardingScreen
import com.lhb.kiotviet_quanly.view.OrderDetailScreen
import com.lhb.kiotviet_quanly.view.OrderScreen
import com.lhb.kiotviet_quanly.view.PaymentMethodScreen
import com.lhb.kiotviet_quanly.view.PaymentScreen
import com.lhb.kiotviet_quanly.view.ProductDetailScreen
import com.lhb.kiotviet_quanly.view.ProductOrderDetailScreen
import com.lhb.kiotviet_quanly.view.RetailCustomers
import com.lhb.kiotviet_quanly.view.ReturnGoodsScreen
import com.lhb.kiotviet_quanly.view.ReturnImportedGoodsScreen
import com.lhb.kiotviet_quanly.view.SignInScreen
import com.lhb.kiotviet_quanly.view.SignUpScreen
import com.lhb.kiotviet_quanly.view.TradeMarkScreen
import com.lhb.kiotviet_quanly.view.components.UpdateOtherInformationScreen
import com.lhb.kiotviet_quanly.view.WarehouseCardScreen
import com.lhb.kiotviet_quanly.view.WelcomeScreen
import com.lhb.kiotviet_quanly.viewmodel.ProductViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenNavigation(){
    val navController = rememberNavController()
    val context = LocalContext.current

    val productViewModel: ProductViewModel = viewModel()
    val allProducts by productViewModel.products.observeAsState(emptyList())


    NavHost(
        navController = navController,
        startDestination = "WelcomeScreen"
    ) {
        composable(ScreenName.OnBoardingScreen.route){ OnBoardingScreen(navController = navController) }
        composable(ScreenName.WelcomeScreen.route){ WelcomeScreen(navController = navController) }
        composable(ScreenName.SignUpScreen.route){ SignUpScreen(navController = navController)}
        composable(ScreenName.SignInScreen.route) { SignInScreen(navController = navController)}
        composable(ScreenName.BottomTav.route){ BottomTav(navController = navController)}
        composable(ScreenName.ForgotPasswordScreen.route){ ForgotPassword(navController = navController)}
        composable(ScreenName.OrderScreen.route){ OrderScreen(navController = navController)}
        composable(ScreenName.ReturnGoodsScreen.route){ ReturnGoodsScreen(navController = navController)}
        composable(ScreenName.CheckInventoryScreen.route){ CheckInventoryScreen(navController = navController)}
        composable(ScreenName.ReturnImportedGoodsScreen.route){ ReturnImportedGoodsScreen(navController = navController)}
        composable(ScreenName.CancelExportScreen.route){ CancelExportScreen(navController = navController)}
        composable(ScreenName.UpdateOtherInformationScreen.route){ UpdateOtherInformationScreen(navController = navController) }
        composable(ScreenName.TrademarkScreen.route){ TradeMarkScreen(navController = navController)}
        composable(ScreenName.NewTrademarkScreen.route){ NewTradeMarkScreen(navController = navController)}
        composable(ScreenName.AddressScreen.route){ AddressScreen(navController = navController)}
        composable(ScreenName.NewAddressScreen.route){ NewAddressScreen(navController = navController)}
        composable(ScreenName.AddDescription.route){ AddDescriptionScreen(navController = navController) }
        composable(ScreenName.WarehouseCardScreen.route){ WarehouseCardScreen(navController = navController)}
        composable(ScreenName.InventoryLevels.route){ InventoryLevels(navController = navController)}

        composable(
            route = "ProductDetailScreen/{productId}",
            arguments = listOf(navArgument("productId"){ type = NavType.StringType })
        ){ navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getString("productId")
            val product = allProducts.firstOrNull{ it.id == productId }
            product?.let {
                ProductDetailScreen(navController = navController, product = it)
            }

        }

        composable(
            "OrderDetailScreen/{selectedProducts}",
            arguments = listOf(navArgument("selectedProducts") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedProductsString = backStackEntry.arguments?.getString("selectedProducts") ?: ""
            val selectedProducts = selectedProductsString.split("|").mapNotNull { id ->
                allProducts.find { it.id == id }
            }.toMutableList()
            OrderDetailScreen(navController,selectedProducts)
        }

        composable(
            route = "ProductOrderDetailScreen/{productId}/{quantity}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType})
        ){ navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getString("productId")
            val quantity = navBackStackEntry.arguments?.getString("quantity")?.toInt() ?: 1
            val product = allProducts.firstOrNull{ it.id == productId }
            product?.let {
                ProductOrderDetailScreen(navController = navController, product = it, initialQuantity = quantity)
            }
        }

        composable(
            route = "UpdateProduct/{productId}",
            arguments = listOf(navArgument("productId") {type = NavType.StringType})
        ){ navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getString("productId")
            val product = allProducts.firstOrNull{ it.id == productId }
            product?.let {
                AddProductScreen(navController = navController, product = it)
            }
        }

        composable(
            route = "Payment/{totalAmount}/{totalQuantity}/{nameCustomer}",
            arguments = listOf(
                navArgument("totalAmount"){type = NavType.IntType},
                navArgument("totalQuantity"){type = NavType.IntType},
                navArgument("nameCustomer"){type = NavType.StringType}
            )
        ){ backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getInt("totalAmount")?: 0
            val totalQuantity = backStackEntry.arguments?.getInt("totalQuantity")?: 0
            val nameCustomer = backStackEntry.arguments?.getString("nameCustomer")?: ""
            PaymentScreen(navController = navController, totalAmount, totalQuantity, nameCustomer)
        }

        composable(ScreenName.RetailCustomersScreen.route){ RetailCustomers(navController = navController)}

        composable(
            route = "PaymentMethodScreen/{totalAmount}",
            arguments = listOf(navArgument("totalAmount"){type = NavType.IntType})
        ){ backStackEntry ->
            val totalAmount = backStackEntry.arguments?.getInt("totalAmount")?: 0
            PaymentMethodScreen(totalAmount = totalAmount, navController = navController)
        }

        composable(ScreenName.AddProductScreen.route){ AddProductScreen(navController = navController) }
    }
}