package com.example.module_3_lesson_5_hw_3_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_3_lesson_5_hw_3_compose.ui.theme.Yellow10
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyApp(
    appViewModel: AppViewModel = viewModel()
) {
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow10)
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.MainScreen.route
        ) {
            composable(ScreenRoutes.MainScreen.route) {
                MainScreen(
                    onNewChallengeClicked = {
                        navController.navigate(ScreenRoutes.NewChallengeScreen.route)
                    },
                    onRecordsClicked = {
                        navController.navigate(ScreenRoutes.RecordsScreen.route)
                    },
                    onAboutClicked = {
                        navController.navigate(ScreenRoutes.AboutScreen.route)
                    }
                )
            }
            composable(ScreenRoutes.NewChallengeScreen.route) {
                NewChallengeScreen(
                    appViewModel = appViewModel,
                    onStartClicked = {
                        navController.navigate(ScreenRoutes.ChallengeScreen.route)
                    }
                )
            }
            composable(ScreenRoutes.ChallengeScreen.route) {
                ChallengeScreen(appViewModel = appViewModel)
            }
            composable(ScreenRoutes.RecordsScreen.route) {
                RecordsScreen()
            }
            composable(ScreenRoutes.AboutScreen.route) {
                AboutScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    onNewChallengeClicked: () -> Unit,
    onRecordsClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large)),
                text = stringResource(id = R.string.main_header),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.padding_large)),
                text = stringResource(id = R.string.main_title),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
            )
        }
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.padding_medium))
                .weight(0.2f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = onNewChallengeClicked
            ) {
                Text(text = stringResource(id = R.string.button_new_challenge))
            }
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = onRecordsClicked
            ) {
                Text(text = stringResource(id = R.string.button_records))
            }
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = onAboutClicked
            ) {
                Text(text = stringResource(id = R.string.button_about))
            }
        }

    }
}

@Composable
fun NewChallengeScreen(
    appViewModel: AppViewModel,
    onStartClicked: () -> Unit
) {
    var selectedChallenge by remember { mutableStateOf(Challenge.SPRINT) }


    // delete after test
    val prefs = SharedPrefs(LocalContext.current)
    Row() {
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        Column() {
            Text(text = prefs.getRecordSprintOne().toString())
            Text(text = prefs.getRecordSprintTwo().toString())
            Text(text = prefs.getRecordSprintThree().toString())
            Text(text = prefs.getRecordSprintFour().toString())
            Text(text = prefs.getRecordSprintFive().toString())
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        Column() {
            Text(text = prefs.getRecordMediumOne().toString())
            Text(text = prefs.getRecordMediumTwo().toString())
            Text(text = prefs.getRecordMediumThree().toString())
            Text(text = prefs.getRecordMediumFour().toString())
            Text(text = prefs.getRecordMediumFive().toString())
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        Column() {
            Text(text = prefs.getRecordMarathonOne().toString())
            Text(text = prefs.getRecordMarathonTwo().toString())
            Text(text = prefs.getRecordMarathonThree().toString())
            Text(text = prefs.getRecordMarathonFour().toString())
            Text(text = prefs.getRecordMarathonFive().toString())
        }
    }
    // delete after test


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Challenge.values().forEach { challenge ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .clickable { selectedChallenge = challenge },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedChallenge == challenge,
                    onClick = { selectedChallenge = challenge }
                )
                Text(
                    text = when (challenge) {
                        Challenge.SPRINT -> stringResource(id = R.string.button_sprint)
                        Challenge.MEDIUM -> stringResource(id = R.string.button_medium)
                        Challenge.MARATHON -> stringResource(id = R.string.button_marathon)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                when (selectedChallenge) {
                    Challenge.SPRINT -> {
                        appViewModel.preStartTimer(10L)
                    }

                    Challenge.MEDIUM -> {
                        appViewModel.preStartTimer(20L)
                    }

                    Challenge.MARATHON -> {
                        appViewModel.preStartTimer(30L)
                    }
                }
                onStartClicked()
            }
        ) {
            Text(text = stringResource(id = R.string.button_start))
        }
    }
}

@Composable
fun ChallengeScreen(
    appViewModel: AppViewModel
) {
    val appUiState by appViewModel.uiState.collectAsState()

    val prefs = SharedPrefs(LocalContext.current)

    if (appUiState.gameCompleted) {
        if (appUiState.currentGame == "Sprint") {
            if (appUiState.clickCount > prefs.getRecordSprintOne()) {
                prefs.setRecordSprintFive(prefs.getRecordSprintFour())
                prefs.setRecordSprintFour(prefs.getRecordSprintThree())
                prefs.setRecordSprintThree(prefs.getRecordSprintTwo())
                prefs.setRecordSprintTwo(prefs.getRecordSprintOne())
                prefs.setRecordSprintOne(appUiState.clickCount)
            }
        }
        if (appUiState.currentGame == "Medium") {
            if (appUiState.clickCount > prefs.getRecordMediumOne()) {
                prefs.setRecordMediumFive(prefs.getRecordMediumFour())
                prefs.setRecordMediumFour(prefs.getRecordMediumThree())
                prefs.setRecordMediumThree(prefs.getRecordMediumTwo())
                prefs.setRecordMediumTwo(prefs.getRecordMediumOne())
                prefs.setRecordMediumOne(appUiState.clickCount)
            }
        }
        if (appUiState.currentGame == "Marathon") {
            if (appUiState.clickCount > prefs.getRecordMarathonOne()) {
                prefs.setRecordMarathonFive(prefs.getRecordMarathonFour())
                prefs.setRecordMarathonFour(prefs.getRecordMarathonThree())
                prefs.setRecordMarathonThree(prefs.getRecordMarathonTwo())
                prefs.setRecordMarathonTwo(prefs.getRecordMarathonOne())
                prefs.setRecordMarathonOne(appUiState.clickCount)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "challenge screen")
        Text(text = appUiState.currentGame)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(id = appUiState.preStartTimerWords)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = appUiState.timerSeconds.toString()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = appUiState.clickCount.toString()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            modifier = Modifier
                .clickable {
                    if (appUiState.clickCountEnabled) {
                        appViewModel.onScreenClicked()
                    }
                },
            text = "CLICK ME"
        )
    }
}

@Composable
fun RecordsScreen() {
    Text(text = "records screen")
}

@Composable
fun AboutScreen() {
    Text(text = "about screen")
}