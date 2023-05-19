package com.pcx.quizapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val configuration = LocalConfiguration.current
    val sh = configuration.screenHeightDp

    val viewModel = viewModel<HomeViewModel>()

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
            .padding(sh.dp / 15),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(HomeEvent.FlagsClick) },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = sh.dp / 75)
        ) {
            Text(
                text = "Flags",
                fontSize = sh.sp / 38
            )
        }
        Spacer(modifier = Modifier.height(sh.dp / 30))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(HomeEvent.CapitalsClick) },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = sh.dp / 75)
        ) {
            Text(
                text = "Capitals",
                fontSize = sh.sp / 38
            )
        }
    }
}