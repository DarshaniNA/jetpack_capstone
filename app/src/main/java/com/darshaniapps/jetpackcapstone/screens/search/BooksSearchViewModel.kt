package com.darshaniapps.jetpackcapstone.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshaniapps.jetpackcapstone.data.DataOrException
import com.darshaniapps.jetpackcapstone.data.Resource
import com.darshaniapps.jetpackcapstone.model.BookItem
import com.darshaniapps.jetpackcapstone.repository.ReaderBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksSearchViewModel @Inject constructor(private val mBooksRepository: ReaderBooksRepository) :
    ViewModel() {

    var mBooksList: List<BookItem> by mutableStateOf(listOf())
    var mIsLoading: Boolean by mutableStateOf(true)

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks(query = "Android")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) return@launch
            mIsLoading = true
            try {
                when (val response = mBooksRepository.getBooksByQuery(query)) {
                    is Resource.Success -> {
                        mBooksList = response.data!!
                        if(mBooksList.isNotEmpty()) mIsLoading = false
                    }
                    is Resource.Error -> {
                        mIsLoading = false
                        Log.d("TAG", "Search Books : Failed Getting Books")
                    }
                    else -> {
                        mIsLoading = true
                    }
                }
            } catch (eX: Exception) {
                Log.d("TAG", "Search Books : ${eX.message.toString()}")
            }
        }
    }
}