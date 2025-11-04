package com.example.tibia.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tibia.repository.HighscoreRepository
import com.example.tibia.model.entity.HighscoreEntity
import com.example.tibia.model.serealizer.CharacterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HighscoreViewModel(
    private val repository: HighscoreRepository
) : ViewModel() {

    private val _highscores = MutableStateFlow<List< HighscoreEntity>>(emptyList())
    val highscores: StateFlow<List<HighscoreEntity>> = _highscores

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    fun hasMorePages() = hasMore
    fun isLoading() = _isLoading.value
    private var hasMore = true
    private val pageSize = 50


    private val _selectedCharacter = MutableStateFlow<CharacterResponse?>(null)
    val selectedCharacter: StateFlow<CharacterResponse?> = _selectedCharacter
    fun clearSelectedCharacter() {
        _selectedCharacter.value = null
    }
    // Função para buscar character
    fun loadCharacter(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.getCharacter(name)
                if (response.isSuccessful) {
                    _selectedCharacter.value = response.body()
                } else {
                    _selectedCharacter.value = null
                }
            } catch (e: Exception) {
                _selectedCharacter.value = null
            }
        }
    }


    fun loadNextPage(world: String, category: String, vocation: String) {
        if (_isLoading.value || !hasMore) return

        _isLoading.value = true
        viewModelScope.launch {
            val lastRankLoaded = _highscores.value.lastOrNull()?.rank ?: 0
            val newItems = repository.getHighscoresPaged(world, category, vocation, lastRankLoaded)
            _highscores.value = (_highscores.value + newItems)

            hasMore = newItems.size == pageSize
            _isLoading.value = false
        }
    }

}
