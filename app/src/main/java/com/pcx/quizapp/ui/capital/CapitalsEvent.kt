package com.pcx.quizapp.ui.capital

sealed interface CapitalsEvent {
    object ButtonAClick : CapitalsEvent
    object ButtonBClick : CapitalsEvent
    object ButtonCClick : CapitalsEvent
    object ButtonDClick : CapitalsEvent
}