package com.pcx.quizapp.ui.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcx.quizapp.util.Screen
import com.pcx.quizapp.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ResultViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var trueCounter by mutableStateOf(0)
    var resultColor by mutableStateOf(Color.White)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val trueCount = savedStateHandle.get<Int>("trueCount")!!
        trueCounter = trueCount

        if (trueCount < 4) {
            resultColor = Color.Red
        }
        if (trueCount in 4..7) {
            resultColor = Color.Yellow
        }
        if (trueCount > 7) {
            resultColor = Color.Green
        }
    }

    fun onEvent(event: ResultEvent) {
        when (event) {
            ResultEvent.TryAgainClick -> {
                sendUiEvent(UiEvent.Navigate(Screen.HOME_SCREEN))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}