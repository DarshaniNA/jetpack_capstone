package com.darshaniapps.jetpackcapstone.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshaniapps.jetpackcapstone.data.Resource
import com.darshaniapps.jetpackcapstone.model.BookItem
import com.darshaniapps.jetpackcapstone.repository.ReaderBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReaderBookDetailsViewModel @Inject constructor(private val mBooksRepository: ReaderBooksRepository) :
    ViewModel() {

        suspend fun getBookInformation(bookId : String) : Resource<BookItem> {
            return mBooksRepository.getBooksDetails(bookId = bookId)
        }
}