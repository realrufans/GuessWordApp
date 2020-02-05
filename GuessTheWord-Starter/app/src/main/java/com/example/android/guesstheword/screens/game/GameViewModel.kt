package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    companion object {
        private const val Done = 0L
        private const val one_second = 1000L
        private const val CountDownTime = 60000L
    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _usedTime = MutableLiveData<Long>()
    val usedTime: LiveData<Long>
        get() = _usedTime


    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _gameEnded = MutableLiveData<Boolean>()
    val gameEnded: LiveData<Boolean>
        get() = _gameEnded


    private lateinit var wordList: MutableList<String>


    init {

        resetList()
        nextWord()
        _score.value = 0
        _word.value = ""
        _gameEnded.value = false







        timer = object : CountDownTimer(CountDownTime, one_second) {
            override fun onFinish() {
                _currentTime.value = Done

            }

            override fun onTick(millisUntilFinished: Long) {

                _currentTime.value = (millisUntilFinished / one_second)

                _usedTime.value = CountDownTime.minus(_currentTime.value ?: 0L)
            }

        }
        timer.start()


    }


    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    fun nextWord() {
        if (!wordList.isEmpty()) {

            resetList()
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    fun onGamefinished() {
        _gameEnded.value = true

    }

    fun onGamefinishedComplete() {
        _gameEnded.value = false

    }


}







