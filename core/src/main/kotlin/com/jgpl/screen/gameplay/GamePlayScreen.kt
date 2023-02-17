package com.jgpl.screen.gameplay

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.*
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

    private lateinit var cursor: Cursor

    private lateinit var gameOverText: GameOverDisplayText
    private lateinit var newGameText: NewGameDisplayText
    private lateinit var restartText: RestartDisplayText
    private lateinit var continueText: ContinueDisplayText
    private lateinit var pauseText: PauseDisplayText
    private lateinit var startText: StartDisplayText
    private lateinit var supportRestartText: SupportRestartDisplayText
    private lateinit var supportContinueDisplayText: SupportContinueDisplayText

    private lateinit var input: GamePlayInput
    private lateinit var state: GamePlayState

    private var deltaTime = 0f

    private var optionSelected: Int = 2

    override fun show() {
        spriteBatch = SpriteBatch()
        scoreText = ScoreDisplayText(spriteBatch)
        gameOverText = GameOverDisplayText(spriteBatch)
        newGameText = NewGameDisplayText(spriteBatch)
        restartText = RestartDisplayText(spriteBatch)
        continueText = ContinueDisplayText(spriteBatch)
        pauseText = PauseDisplayText(spriteBatch)
        startText = StartDisplayText(spriteBatch)
        supportRestartText = SupportRestartDisplayText(spriteBatch)
        supportContinueDisplayText = SupportContinueDisplayText(spriteBatch)
        scoreArea = ScoreArea()

        input = GamePlayInput()
        state = GamePlayState()

        darkLayer = DarkLayer()

        cursor = Cursor(2) { optionSelected = it }

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

        if (state.isGamePaused) {
            val inputPressed = input.getMove()
            cursor.move(inputPressed)

            if (input.getOptionSelection()) {
                when (optionSelected) {
                    1 -> restart()
                    2 -> state.pause()
                }
            }
            return
        }

        optionSelected = 2
        cursor.move(CursorMovement.Up)

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
        if (state.isGamePaused) {
            cursor.render()
        }

        spriteBatch.begin()
        scoreText.render(state.score)
        gameOverText.render(state.isGameFinished)
        newGameText.render(state.isGameFinished)
        pauseText.render(state.isGamePaused)
        startText.render(!state.isGameStarted)
        continueText.render(state.isGamePaused)
        restartText.render(state.isGamePaused)
        supportRestartText.render(state.isGamePaused && optionSelected == 1)
        supportContinueDisplayText.render(state.isGamePaused && optionSelected == 2)
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
        newGameText.dispose()
        pauseText.dispose()
        spriteBatch.dispose()
        scoreArea.dispose()
        cursor.dispose()
        continueText.dispose()
        restartText.dispose()
        supportRestartText.dispose()
        supportContinueDisplayText.dispose()
    }

}
