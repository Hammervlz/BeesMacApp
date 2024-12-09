package com.maximov.beesmacapp


data class Product (
    val name: String,
    val imageRes: Int,
    val price: Double,
    val additionalImages: List<Int> = listOf(),
    val quantity: Int
)