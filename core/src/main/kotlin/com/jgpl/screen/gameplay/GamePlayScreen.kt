package com.jgpl.screen.gameplay

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.Difficult
import com.jgpl.entity.*
import com.jgpl.utils.GameColor
import com.jgpl.utils.UpdateScreen
import com.jgpl.entity.font.GameOverFont
import com.jgpl.entity.font.PauseFont
import com.jgpl.entity.font.RestartFont
import com.jgpl.entity.font.ScoreFont

class GamePlayScreen(
    private val difficult: Difficult
) : Screen, UpdateScreen {

    private lateinit var spriteBatch: SpriteBatch

    private lateinit var board: Board
    private lateinit var snake: Snake

    private lateinit var darkLayer: DarkLayer

    private lateinit var scoreFont: ScoreFont
    private lateinit var scoreArea: ScoreArea

    private lateinit var gameOverFont: GameOverFont
    private lateinit var restartFont: RestartFont
    private lateinit var pauseFont: PauseFont

    private lateinit var input: GamePlayInput
    private lateinit var state: GamePlayState

    private var deltaTime = 0f

    override fun show() {
        spriteBatch = SpriteBatch()
        scoreFont = ScoreFont(spriteBatch)
        gameOverFont = GameOverFont(spriteBatch)
        restartFont = RestartFont(spriteBatch)
        pauseFont = PauseFont(spriteBatch)
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
        scoreFont.render(state.score)
        gameOverFont.render(state.isGameFinished)
        restartFont.render(state.isGameFinished)
        pauseFont.render(state.isGamePaused)
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
        scoreFont.dispose()
        darkLayer.dispose()
        gameOverFont.dispose()
        restartFont.dispose()
        pauseFont.dispose()
        spriteBatch.dispose()
        scoreArea.dispose()
    }

}
