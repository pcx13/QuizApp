package com.pcx.quizapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcx.quizapp.util.Screen
import com.pcx.quizapp.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.CapitalsClick ->
                sendUiEvent(UiEvent.Navigate(Screen.CAPITALS_SCREEN))

            HomeEvent.FlagsClick ->
                sendUiEvent(UiEvent.Navigate(Screen.FLAGS_SCREEN))
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}