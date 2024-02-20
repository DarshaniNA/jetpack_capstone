package com.darshaniapps.jetpackcapstone.network

import com.darshaniapps.jetpackcapstone.model.BooksResponse
import com.darshaniapps.jetpackcapstone.model.BookItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ReaderApiService {
    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): BooksResponse

    @GET("volumes/{bookId}")
    suspend fun getABookDetails(@Path("bookId") bookId: String): BookItem
}