package com.maximov.beesmacapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

@Composable
fun NavGraph(startDestination: String = "main_screen") {
    val navController = rememberNavController()
    val cartItems = remember { mutableStateListOf<Product>() }
    val httpClient = HttpClient()

    fun addToCart(item: Product) {
        cartItems.add(item)
    }

    fun removeFromCart(item: Product) {
        cartItems.remove(item)
    }
    fun clearCart() {
        cartItems.clear()
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("main_screen") {
            MainScreen(
                onNavigate = { category ->
                    navController.navigate("${category}_screen")
                },
                onNavigateToCart = {
                    navController.navigate("cart_screen")
                },
                cartItems = cartItems.map { it.name }
            )
        }
        composable("Сумки_screen") {
            BagsScreen(
                onAddToCart = { item: Product -> addToCart(item) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("Кошельки_screen") {
            WalletScreen(
                onAddToCart = { item: Product -> addToCart(item) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("Ремни_screen") {
            BeltScreen(
                onAddToCart = { item: Product -> addToCart(item) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("Брелки_screen") {
            KeychainScreen(
                onAddToCart = { item: Product -> addToCart(item) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("cart_screen") {
            CartScreen(
                cartItems = cartItems,
                onRemoveFromCart = { item -> removeFromCart(item) },
                onBack = { navController.popBackStack() },
                onCheckout = { navController.navigate("checkout_screen") }
            )
        }
        composable("checkout_screen") {
            CheckoutScreen(
                orderItem = cartItems.map { OrderItem(it.name, it.quantity, it.price) },
                onSubmit = {
                    clearCart()
                    // После успешной отправки заказа, вернитесь на главный экран
                    navController.popBackStack("main_screen", inclusive = false)
                }
            )
        }
    }
}

