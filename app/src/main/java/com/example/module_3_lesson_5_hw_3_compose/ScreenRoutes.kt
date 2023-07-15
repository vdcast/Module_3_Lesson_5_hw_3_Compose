package com.example.module_3_lesson_5_hw_3_compose

sealed class ScreenRoutes(val route: String) {
    object MainScreen : ScreenRoutes("main_screen")
    object NewChallengeScreen : ScreenRoutes("new_challenge_screen")
    object ChallengeScreen : ScreenRoutes("challenge_screen")
    object RecordsScreen : ScreenRoutes("records_screen")
    object AboutScreen : ScreenRoutes("about_screen")
}