package com.jgpl

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.*
import com.jgpl.utils.GameColor
import com.jgpl.utils.font.GameOverFont
import com.jgpl.utils.font.RestartFont
import com.jgpl.utils.font.ScoreFont

class Game : ApplicationAdapter() {

    private lateinit var spriteBatch: SpriteBatch

    private lateinit var board: Board
    private lateinit var snake: Snake
    private lateinit var darkLayer: DarkLayer
    private lateinit var scoreFont: ScoreFont
    private lateinit var scoreArea: ScoreArea
    private lateinit var gameOverFont: GameOverFont
    private lateinit var restartFont: RestartFont

    private val difficult = Difficult.Hard
    private var lastDirection: Direction = Direction.Up
    private var deltaTime = 0f

    private var isGameFinished = false
    private var isGamePaused = false
    private var score: Int = 0

    override fun create() {
        spriteBatch = SpriteBatch()
        scoreFont = ScoreFont(spriteBatch)
        gameOverFont = GameOverFont(spriteBatch)
        restartFont = RestartFont(spriteBatch)
        scoreArea = ScoreArea()

        snake = Snake { isGameFinished = true }
        board = Board(snake) { score += 1 }
        darkLayer = DarkLayer()
    }

    private fun update() {
        if (isGameFinished) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                restart()
            }
            return
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isGamePaused = !isGamePaused
        }

        if (isGamePaused) return

        lastDirection = getInput()

        deltaTime += Gdx.graphics.deltaTime
        if (deltaTime > difficult.speed) {
            deltaTime = 0f

            snake.move(lastDirection)
            board.update()
        }
    }

    override fun render() {
        ScreenUtils.clear(GameColor.Indigo.toGdxColor())

        update()

        scoreArea.render()
        board.render()
        snake.render()
        darkLayer.render(isGameFinished || isGamePaused)

        spriteBatch.begin()
        scoreFont.render(score)
        gameOverFont.render(isGameFinished)
        restartFont.render(isGameFinished)
        spriteBatch.end()
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

    private fun restart() {
        score = 0
        isGameFinished = false
        lastDirection = Direction.Up
        snake = Snake { isGameFinished = true }
        board = Board(snake) { score += 1 }
    }

    override fun dispose() {
        snake.dispose()
        board.dispose()
        scoreFont.dispose()
        darkLayer.dispose()
        gameOverFont.dispose()
        restartFont.dispose()
        spriteBatch.dispose()
        scoreArea.dispose()
    }

}

sealed class Difficult(val speed: Float) {
    object Easy : Difficult(0.25f)
    object Normal : Difficult(0.10f)
    object Hard : Difficult(0.05f)
    object Extreme : Difficult(0.025f)
}
