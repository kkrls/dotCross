package com.dotcross_app.dotcross.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    // Home screen state
    private val _uiState = MutableStateFlow(HomeUiState())
    // Backing property for immutability
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}