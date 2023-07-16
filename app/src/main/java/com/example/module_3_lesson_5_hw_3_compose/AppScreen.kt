package com.example.module_3_lesson_5_hw_3_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.module_3_lesson_5_hw_3_compose.ui.theme.Yellow20

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
                ChallengeScreen(
                    appViewModel = appViewModel,
                    onOkClicked = {
                        navController.popBackStack(
                            route = ScreenRoutes.NewChallengeScreen.route,
                            inclusive = false
                        )
                    }
                )
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.new_challenge_title),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
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
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                when (selectedChallenge) {
                    Challenge.SPRINT -> { appViewModel.preStartTimer(10L) }
                    Challenge.MEDIUM -> { appViewModel.preStartTimer(20L) }
                    Challenge.MARATHON -> { appViewModel.preStartTimer(30L) }
                }
                onStartClicked()
            }
        ) {
            Text(text = stringResource(id = R.string.button_start))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeScreen(
    appViewModel: AppViewModel,
    onOkClicked: () -> Unit
) {
    val appUiState by appViewModel.uiState.collectAsState()
    val prefs = SharedPrefs(LocalContext.current)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = appUiState.currentGame,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(id = appUiState.preStartTimerWords),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Row(
            modifier = Modifier.fillMaxWidth(0.75f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.challenge_time, appUiState.timerSeconds.toString()),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = stringResource(id = R.string.challenge_score, appUiState.clickCount.toString()),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_medium)),
            elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.padding_xsmall)),
            colors = CardDefaults.cardColors(Yellow20),
            onClick = {
                if (appUiState.clickCountEnabled) {
                    appViewModel.onScreenClicked()
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CLICK HERE",
                )
            }
        }
    }
    if (appUiState.gameCompleted) {
        AlertDialog(
            onDismissRequest = {  },
            title = {
                Text(text = stringResource(id = R.string.alert_title))
            },
            text = {
                Text(
                    stringResource(
                        id = R.string.alert_text,
                        appUiState.currentGame,
                        appUiState.clickCount,
                        when (appUiState.currentGame) {
                            "Sprint" -> { prefs.getRecordSprintOne() }
                            "Medium" -> { prefs.getRecordMediumOne() }
                            else -> { prefs.getRecordMarathonOne() }
                        },
                        appUiState.newRecord
                    ),
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        appViewModel.resetGame()
                        onOkClicked()
                    }
                ) {
                    Text(stringResource(id = R.string.alert_button))
                }
            }
        )

        if (appUiState.currentGame == "Sprint") {
            if (appUiState.clickCount > prefs.getRecordSprintOne()) {
                appViewModel.newRecord()
                prefs.setRecordSprintFive(prefs.getRecordSprintFour())
                prefs.setRecordSprintFour(prefs.getRecordSprintThree())
                prefs.setRecordSprintThree(prefs.getRecordSprintTwo())
                prefs.setRecordSprintTwo(prefs.getRecordSprintOne())
                prefs.setRecordSprintOne(appUiState.clickCount)
            }
        }
        if (appUiState.currentGame == "Medium") {
            if (appUiState.clickCount > prefs.getRecordMediumOne()) {
                appViewModel.newRecord()
                prefs.setRecordMediumFive(prefs.getRecordMediumFour())
                prefs.setRecordMediumFour(prefs.getRecordMediumThree())
                prefs.setRecordMediumThree(prefs.getRecordMediumTwo())
                prefs.setRecordMediumTwo(prefs.getRecordMediumOne())
                prefs.setRecordMediumOne(appUiState.clickCount)
            }
        }
        if (appUiState.currentGame == "Marathon") {
            if (appUiState.clickCount > prefs.getRecordMarathonOne()) {
                appViewModel.newRecord()
                prefs.setRecordMarathonFive(prefs.getRecordMarathonFour())
                prefs.setRecordMarathonFour(prefs.getRecordMarathonThree())
                prefs.setRecordMarathonThree(prefs.getRecordMarathonTwo())
                prefs.setRecordMarathonTwo(prefs.getRecordMarathonOne())
                prefs.setRecordMarathonOne(appUiState.clickCount)
            }
        }
    }
}

@Composable
fun RecordsScreen() {
    val prefs = SharedPrefs(LocalContext.current)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.records_hall_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.sprint),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Text(text = prefs.getRecordSprintOne().toString())
                Text(text = prefs.getRecordSprintTwo().toString())
                Text(text = prefs.getRecordSprintThree().toString())
                Text(text = prefs.getRecordSprintFour().toString())
                Text(text = prefs.getRecordSprintFive().toString())
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.medium),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Text(text = prefs.getRecordMediumOne().toString())
                Text(text = prefs.getRecordMediumTwo().toString())
                Text(text = prefs.getRecordMediumThree().toString())
                Text(text = prefs.getRecordMediumFour().toString())
                Text(text = prefs.getRecordMediumFive().toString())
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.marathon),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Text(text = prefs.getRecordMarathonOne().toString())
                Text(text = prefs.getRecordMarathonTwo().toString())
                Text(text = prefs.getRecordMarathonThree().toString())
                Text(text = prefs.getRecordMarathonFour().toString())
                Text(text = prefs.getRecordMarathonFive().toString())
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
    }
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.75f),
            text = stringResource(id = R.string.about_title),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}