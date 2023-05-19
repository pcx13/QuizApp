package com.pcx.quizapp.ui.capital

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcx.quizapp.R
import com.pcx.quizapp.data.DatabaseHandler
import com.pcx.quizapp.data.Quiz
import com.pcx.quizapp.data.QuizDao
import com.pcx.quizapp.util.Screen
import com.pcx.quizapp.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CapitalsViewModel : ViewModel() {

    var questions = arrayListOf<Quiz>()
    var trueAnswer by mutableStateOf<Quiz?>(null)
    var falseAnswer = arrayListOf<Quiz>()
    var allOptions = hashSetOf<Quiz>()
    var drawableId by mutableStateOf(R.drawable.ic_launcher_foreground)

    var questionCounter by mutableStateOf(0)
    var trueCounter by mutableStateOf(0)
    var falseCounter by mutableStateOf(0)

    var buttonA by mutableStateOf("")
    var buttonB by mutableStateOf("")
    var buttonC by mutableStateOf("")
    var buttonD by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    lateinit var db: DatabaseHandler

    fun onEvent(event: CapitalsEvent, context: Context) {
        when (event) {
            is CapitalsEvent.ButtonAClick -> {
                correctQuiz(buttonA)
                quizControl(context)
            }

            is CapitalsEvent.ButtonBClick -> {
                correctQuiz(buttonB)
                quizControl(context)
            }

            is CapitalsEvent.ButtonCClick -> {
                correctQuiz(buttonC)
                quizControl(context)
            }

            is CapitalsEvent.ButtonDClick -> {
                correctQuiz(buttonD)
                quizControl(context)
            }
        }
    }

    fun getQuiz(context: Context) {
        trueAnswer = questions[questionCounter]
        falseAnswer = QuizDao().get3WrongAnswer(db, trueAnswer!!.id)

        allOptions = HashSet()
        allOptions.apply {
            add(trueAnswer!!)
            add(falseAnswer[0])
            add(falseAnswer[1])
            add(falseAnswer[2])
        }

        buttonA = allOptions.elementAt(0).capital
        buttonB = allOptions.elementAt(1).capital
        buttonC = allOptions.elementAt(2).capital
        buttonD = allOptions.elementAt(3).capital

        drawableId = context.resources.getIdentifier(
            trueAnswer!!.flag, "drawable", context.packageName
        )
    }

    private fun quizControl(context: Context) {
        questionCounter++

        if (questionCounter > 9) {
            sendUiEvent(
                UiEvent.Navigate(
                    Screen.RESULT_SCREEN + "?trueCount=$trueCounter"
                )
            )
        } else {
            getQuiz(context)
        }
    }

    private fun correctQuiz(buttonText: String) {
        val correctAnswer = trueAnswer!!.capital

        if (buttonText == correctAnswer) {
            trueCounter++
        } else {
            falseCounter++
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}