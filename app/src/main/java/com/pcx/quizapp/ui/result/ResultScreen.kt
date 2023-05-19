package com.pcx.quizapp.ui.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pcx.quizapp.ui.theme.ButtonColor
import com.pcx.quizapp.util.UiEvent

@Composable
fun ResultScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val sh = LocalConfiguration.current.screenHeightDp
    val viewModel = viewModel<ResultViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Success rate ",
                fontSize = sh.sp / 30,
                color = Color.White
            )
            Text(
                text = "${viewModel.trueCounter * 10}%",
                fontSize = sh.sp / 30,
                color = viewModel.resultColor
            )
        }
        Spacer(modifier = Modifier.height(sh.dp / 30))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(ResultEvent.TryAgainClick) },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = sh.dp / 75)
        ) {
            Text(
                text = "Try again",
                fontSize = sh.sp / 38
            )
        }
    }
}