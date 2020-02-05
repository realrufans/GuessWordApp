package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finaltime: Long) : ViewModel() {

    private val _playAgain = MutableLiveData<Boolean>()
    val playAgain: LiveData<Boolean>
        get() = _playAgain


    //var score = finalscore
    var timet = finaltime


    init {


        _playAgain.value = false
        Log.i("ScoreViewModel", "Final score")
    }

    fun onPlayAgain() {
        _playAgain.value = true
    }

}