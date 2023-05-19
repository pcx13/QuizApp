package com.pcx.quizapp.ui.flag

sealed interface FlagsEvent {
    object ButtonAClick : FlagsEvent
    object ButtonBClick : FlagsEvent
    object ButtonCClick : FlagsEvent
    object ButtonDClick : FlagsEvent
}