package com.example.module_3_lesson_5_hw_3_compose

data class AppUiState(
    val timerSeconds: Long = 0L,
    val preStartTimerWords: Int = R.string.empty,
    val clickCount: Int = 0,
    val clickCountEnabled: Boolean = false,
    val gameCompleted: Boolean = false,
    val currentGame: String = ""
)