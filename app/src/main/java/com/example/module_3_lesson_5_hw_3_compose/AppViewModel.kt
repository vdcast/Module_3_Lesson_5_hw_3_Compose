package com.example.module_3_lesson_5_hw_3_compose

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

class AppViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private lateinit var timer: DisposableSubscriber<Long>

    fun preStartTimer(until: Long) {
        resetGame()

        timer = object : DisposableSubscriber<Long>() {
            override fun onNext(t: Long?) {
                var remainingSeconds = 0L
                var preStartTimerWords = 1
                if (t != null) {
                    remainingSeconds = 3 - t
                }
                when (remainingSeconds) {
                    3L -> preStartTimerWords = R.string.ready
                    2L -> preStartTimerWords = R.string.steady
                    1L -> {
                        preStartTimerWords = R.string.go
                        startGameTimer(until = until)
                        _uiState.update { currentState ->
                            currentState.copy(clickCountEnabled = true)
                        }
                    }
                }
                _uiState.update { currentState ->
                    currentState.copy(
                        preStartTimerWords = preStartTimerWords,
                        timerSeconds = until
                    )
                }
                Log.d("MYLOG", "preStart timer : $remainingSeconds")
            }

            override fun onError(t: Throwable?) {  }
            override fun onComplete() {
                Log.d("MYLOG", "preStart DONE :)")
            }

        }
        Flowable.intervalRange(0, 3, 0, 2, TimeUnit.SECONDS)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(timer)
    }

    private fun startGameTimer(until: Long) {
        timer = object : DisposableSubscriber<Long>() {
            override fun onNext(t: Long?) {
                var remainingSeconds = 0L
                if (t != null) {
                    remainingSeconds = until - t
                }
                _uiState.update { currentState ->
                    currentState.copy(timerSeconds = remainingSeconds)
                }
                Log.d("MYLOG", "main timer : $remainingSeconds")
            }

            override fun onError(t: Throwable?) {  }
            override fun onComplete() {
                Log.d("MYLOG", "main timer DONE :)")
                stopTimer()
                _uiState.update { currentState ->
                    currentState.copy(clickCountEnabled = false)
                }
            }

        }
        Flowable.intervalRange(0, until + 1, 0, 1, TimeUnit.SECONDS)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(timer)
    }

    private fun stopTimer() {
        if (::timer.isInitialized) {
            timer.dispose()
        }
    }

    fun onScreenClicked() {
        _uiState.update { currentState ->
            currentState.copy(clickCount = currentState.clickCount + 1)
        }
    }

    private fun resetGame() {
        stopTimer()
        _uiState.value = AppUiState(
            timerSeconds = 0L,
            preStartTimerWords = R.string.empty,
            clickCount = 0,
            clickCountEnabled = false
        )
    }

}