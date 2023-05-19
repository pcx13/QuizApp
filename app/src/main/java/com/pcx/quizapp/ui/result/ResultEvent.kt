package com.pcx.quizapp.ui.result

sealed interface ResultEvent {
    object TryAgainClick : ResultEvent
}