package com.darshaniapps.jetpackcapstone.model

data class BooksResponse(
    val items: List<BookItem>,
    val kind: String,
    val totalItems: Int
)