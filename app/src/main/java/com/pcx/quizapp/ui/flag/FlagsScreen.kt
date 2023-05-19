package com.pcx.quizapp.ui.flag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pcx.quizapp.R
import com.pcx.quizapp.data.DatabaseHandler
import com.pcx.quizapp.data.QuizDao
import com.pcx.quizapp.ui.theme.ButtonColor
import com.pcx.quizapp.util.UiEvent

@Composable
fun FlagsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val sh = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current
    val viewModel = viewModel<FlagsViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.db = DatabaseHandler(context)
        viewModel.questions = QuizDao().get10RandomFlag(viewModel.db)

        viewModel.getQuiz(context)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sh.dp / 19),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_true),
                    contentDescription = "True",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(sh.dp / 15)
                )
                Text(
                    text = "${viewModel.trueCounter}",
                    color = ButtonColor,
                    fontSize = sh.sp / 34
                )
            }
            Text(
                text = "${viewModel.questionCounter + 1}/10",
                color = ButtonColor,
                fontSize = sh.sp / 30
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_false),
                    contentDescription = "False",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(sh.dp / 15)
                )
                Text(
                    text = "${viewModel.falseCounter}",
                    color = ButtonColor,
                    fontSize = sh.sp / 34
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(sh.dp / 3),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = viewModel.drawableId
                ),
                contentDescription = "Country",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(sh.dp / 38)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onEvent(FlagsEvent.ButtonAClick, context) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = ButtonColor
                ),
                contentPadding = PaddingValues(sh.dp / 75)
            ) {
                Text(
                    text = viewModel.buttonA,
                    fontSize = sh.sp / 42,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onEvent(FlagsEvent.ButtonBClick, context) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = ButtonColor
                ),
                contentPadding = PaddingValues(sh.dp / 75)
            ) {
                Text(
                    text = viewModel.buttonB,
                    fontSize = sh.sp / 42,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onEvent(FlagsEvent.ButtonCClick, context) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = ButtonColor
                ),
                contentPadding = PaddingValues(sh.dp / 75)
            ) {
                Text(
                    text = viewModel.buttonC,
                    fontSize = sh.sp / 42,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onEvent(FlagsEvent.ButtonDClick, context) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = ButtonColor
                ),
                contentPadding = PaddingValues(sh.dp / 75)
            ) {
                Text(
                    text = viewModel.buttonD,
                    fontSize = sh.sp / 42,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}