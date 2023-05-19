package com.pcx.quizapp.util

sealed interface UiEvent {
    data class Navigate(val route: String) : UiEvent
}