package com.pcx.quizapp.ui.home

sealed interface HomeEvent {
    object FlagsClick : HomeEvent
    object CapitalsClick : HomeEvent
}