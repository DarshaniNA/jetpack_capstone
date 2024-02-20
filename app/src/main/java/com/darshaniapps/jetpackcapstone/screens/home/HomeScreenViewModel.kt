package com.darshaniapps.jetpackcapstone.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darshaniapps.jetpackcapstone.data.DataOrException
import com.darshaniapps.jetpackcapstone.model.MBook
import com.darshaniapps.jetpackcapstone.repository.FireRepository
import com.darshaniapps.jetpackcapstone.repository.ReaderBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val mFireRepository: FireRepository
) : ViewModel() {

    var retrievedBooksData: MutableState<DataOrException<List<MBook>, Boolean, Exception>> = mutableStateOf(
        DataOrException(
            listOf(), true, Exception("")
        )
    )

    init {
        getAllBooksStored()
    }

    private fun getAllBooksStored() {
        viewModelScope.launch {
            retrievedBooksData.value.loading = true
            retrievedBooksData.value = mFireRepository.getAllBooksStored()
            if (retrievedBooksData.value.data.isNullOrEmpty().not()) retrievedBooksData.value.loading = false
        }
    }
}