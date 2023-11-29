package com.example.anti_chess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameStateVM: ViewModel() {

    private val _uiState = MutableStateFlow(GameStateUI())
    val uiState: StateFlow<GameStateUI> = _uiState.asStateFlow()

    var pointedCell by mutableStateOf<Int?>(null)
        private set

    fun pointCell(index: Int) {
        _uiState.value = _uiState.value.copy(pointedCell = index)
    }


}