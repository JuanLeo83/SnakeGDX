package com.jgpl.screen.gameplay

import com.jgpl.entity.Direction

data class GamePlayState(
    var isGameStarted: Boolean = false,
    var isGamePaused: Boolean = false,
    var isGameFinished: Boolean = false,
    var lastDirection: Direction = Direction.Up,
    var score: Int = 0
) {

    fun reset() {
        isGameStarted = false
        isGamePaused = false
        isGameFinished = false
        lastDirection = Direction.Up
        score = 0
    }

    fun start() {
        isGameStarted = true
        isGamePaused = false
        isGameFinished = false
    }

    fun pause(){
        isGamePaused = !isGamePaused
    }

    fun finish() {
        isGameFinished = true
    }

    fun addScore() {
        score += 1
    }

    fun isPlaying() = !isGameFinished && !isGamePaused
}
