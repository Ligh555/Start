package com.ligh.viewModul

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ligh.database.WordRepository
import com.ligh.room.Word
import kotlinx.coroutines.launch

class WordViewModul (private val repository :WordRepository):ViewModel() {
    val words = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}