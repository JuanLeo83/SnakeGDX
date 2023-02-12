package com.jgpl.screen.gameplay

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.Board
import com.jgpl.entity.DarkLayer
import com.jgpl.entity.ScoreArea
import com.jgpl.entity.Snake
import com.jgpl.entity.displaytext.*
import com.jgpl.utils.GameColor
import com.jgpl.utils.UpdateScreen
import com.jgpl.utils.difficult

class GamePlayScreen : Screen, UpdateScreen {

    private lateinit var spriteBatch: SpriteBatch

    private lateinit var board: Board
    private lateinit var snake: Snake

    private lateinit var darkLayer: DarkLayer

    private lateinit var scoreText: ScoreDisplayText
    private lateinit var scoreArea: ScoreArea

    private lateinit var gameOverText: GameOverDisplayText
    private lateinit var restartText: RestartDisplayText
    private lateinit var pauseText: PauseDisplayText
    private lateinit var startText: StartDisplayText

    private lateinit var input: GamePlayInput
    private lateinit var state: GamePlayState

    private var deltaTime = 0f

    override fun show() {
        spriteBatch = SpriteBatch()
        scoreText = ScoreDisplayText(spriteBatch)
        gameOverText = GameOverDisplayText(spriteBatch)
        restartText = RestartDisplayText(spriteBatch)
        pauseText = PauseDisplayText(spriteBatch)
        startText = StartDisplayText(spriteBatch)
        scoreArea = ScoreArea()

        input = GamePlayInput()
        state = GamePlayState()

        darkLayer = DarkLayer()

        initEntities()
    }

    override fun update(delta: Float) {
        if (!state.isGameStarted) {
            if (input.getStart()) {
                state.start()
            }
            return
        }

        if (state.isGameFinished) {
            if (input.getRestart()) {
                restart()
            }
            return
        }

        if (input.getPause()) {
            state.pause()
        }

        if (state.isGamePaused) return

        state.lastDirection = input.getDirection(state.lastDirection)

        deltaTime += delta
        if (deltaTime > difficult.speed) {
            deltaTime = 0f

            snake.move(state.lastDirection)
            board.update()
        }
    }

    private fun initEntities() {
        snake = Snake { state.finish() }
        board = Board(snake) { state.addScore() }
    }

    private fun restart() {
        state.reset()
        initEntities()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(GameColor.Indigo.toGdxColor())

        scoreArea.render()
        board.render()
        snake.render()
        darkLayer.render(!state.isPlaying())

        spriteBatch.begin()
        scoreText.render(state.score)
        gameOverText.render(state.isGameFinished)
        restartText.render(state.isGameFinished)
        pauseText.render(state.isGamePaused)
        startText.render(!state.isGameStarted)
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) {}

    override fun pause() {
        state.pause()
    }

    override fun resume() {}

    override fun hide() {
        state.pause()
    }

    override fun dispose() {
        snake.dispose()
        board.dispose()
        scoreText.dispose()
        darkLayer.dispose()
        gameOverText.dispose()
        restartText.dispose()
        pauseText.dispose()
        spriteBatch.dispose()
        scoreArea.dispose()
    }

}
