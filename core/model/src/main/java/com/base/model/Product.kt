package com.base.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String? = null,
    val description: String? = null
)
