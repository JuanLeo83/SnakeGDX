package com.jgpl

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.*

class Game : ApplicationAdapter() {

    private lateinit var board: Board
    private lateinit var snake: Snake

    private val difficult = Difficult.Hard
    private var lastDirection: Direction = Direction.Up
    private var deltaTime = 0f

    private var isGameFinished = false

    override fun create() {
        snake = Snake { isGameFinished = true }
        board = Board(snake)
    }

    override fun render() {
        if (isGameFinished) return

        ScreenUtils.clear(Color.BLACK)

        lastDirection = getInput()

        deltaTime += Gdx.graphics.deltaTime
        if (deltaTime > difficult.speed) {
            deltaTime = 0f

            snake.move(lastDirection)
            board.update()
        }

        board.render()
        snake.render()
    }

    private fun getInput(): Direction {
        return if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (lastDirection != Direction.Down) {
                Direction.Up
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (lastDirection != Direction.Left) {
                Direction.Right
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (lastDirection != Direction.Up) {
                Direction.Down
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (lastDirection != Direction.Right) {
                Direction.Left
            } else lastDirection
        } else {
            lastDirection
        }
    }

    override fun dispose() {
        snake.dispose()
    }

}

sealed class Difficult(val speed: Float) {
    object Easy : Difficult(0.25f)
    object Normal : Difficult(0.10f)
    object Hard : Difficult(0.05f)
    object Extreme : Difficult(0.025f)
}
