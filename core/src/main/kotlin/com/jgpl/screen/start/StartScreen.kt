package com.jgpl.screen.start

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.Cursor
import com.jgpl.entity.DarkLayer
import com.jgpl.entity.Direction
import com.jgpl.entity.Snake
import com.jgpl.entity.displaytext.*
import com.jgpl.utils.*

class StartScreen(
    private val play: () -> Unit
) : Screen, UpdateScreen {

    private lateinit var spriteBatch: SpriteBatch
    private lateinit var input: StartInput
    private lateinit var state: StartState
    private lateinit var cursor: Cursor
    private lateinit var darkLayer: DarkLayer
    private lateinit var snake1: Snake
    private lateinit var snake2: Snake
    private lateinit var snake3: Snake

    private lateinit var titleText: TitleDisplayText
    private lateinit var playText: PlayDisplayText
    private lateinit var difficultText: DifficultDisplayText
    private lateinit var languageText: LanguageDisplayText
    private lateinit var supportText: SupportOptionsDisplayText
    private lateinit var supportStartText: SupportStartDisplayText

    private var difficultSelected: Int = 1
    private var difficultyOptions =  setDifficultOptions()
    private fun setDifficultOptions(): List<String> {
        return listOf(
            GameText.Easy.get(language).text,
            GameText.Normal.get(language).text,
            GameText.Hard.get(language).text,
            GameText.Extreme.get(language).text,
        )
    }

    private var languageSelected: Int = 0
    private var languageOptions = setLangOptions()
    private fun setLangOptions(): List<String> {
        return listOf(
            GameText.English.get(language).text,
            GameText.Spanish.get(language).text,
        )
    }

    private var optionSelected: Int = 3

    private var lastDirection1: Direction = Direction.Up
    private var lastDirection2: Direction = Direction.Right
    private var lastDirection3: Direction = Direction.Down
    private var deltaTime: Float = 0f
    private var time: Float = 0f

    override fun show() {
        spriteBatch = SpriteBatch()
        input = StartInput()
        state = StartState()

        darkLayer = DarkLayer()

        titleText = TitleDisplayText(spriteBatch)
        playText = PlayDisplayText(spriteBatch)
        difficultText = DifficultDisplayText(spriteBatch)
        languageText = LanguageDisplayText(spriteBatch)
        supportText = SupportOptionsDisplayText(spriteBatch)
        supportStartText = SupportStartDisplayText(spriteBatch)

        cursor = Cursor { optionSelected = it }

        snake1 = Snake(300, 500) {}
        snake2 = Snake(600, 250) {}
        snake3 = Snake(900, 800) {}
    }

    override fun update(delta: Float) {
        val inputPressed = input.getMove()
        cursor.move(inputPressed)

        if (input.nextOption()) {
            when (optionSelected) {
                1 -> {
                    languageSelected++
                    if (languageSelected == languageOptions.size) languageSelected = 0
                    setLanguage(languageSelected)
                    languageOptions = setLangOptions()
                    difficultyOptions = setDifficultOptions()
                }
                2 -> {
                    difficultSelected++
                    if (difficultSelected == difficultyOptions.size) difficultSelected = 0
                    setDifficult(difficultSelected)
                }
                3 -> play()
            }
        }

        time += delta
        if (time >= 1f) {
            time = 0f
            snake1.addPart()
            snake2.addPart()
            snake3.addPart()
            lastDirection1 = input.getRandomDirection(lastDirection1)
            lastDirection2 = input.getRandomDirection(lastDirection2)
            lastDirection3 = input.getRandomDirection(lastDirection3)
        }

        deltaTime += delta
        if (deltaTime > difficult.speed) {
            deltaTime = 0f
            snake1.move(lastDirection1)
            snake2.move(lastDirection2)
            snake3.move(lastDirection3)
        }
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(GameColor.Indigo.toGdxColor())

        snake1.render()
        snake2.render()
        snake3.render()
        darkLayer.render(true)
        cursor.render()

        spriteBatch.begin()
        titleText.render()
        playText.render()
        difficultText.render(difficultyOptions[difficultSelected])
        languageText.render(languageOptions[languageSelected])
        supportText.render(optionSelected != 3)
        supportStartText.render(optionSelected == 3)
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) {}

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        spriteBatch.dispose()
        titleText.dispose()
        cursor.dispose()
        playText.dispose()
        difficultText.dispose()
        languageText.dispose()
        supportText.dispose()
        supportStartText.dispose()
        darkLayer.dispose()
        snake1.dispose()
        snake2.dispose()
        snake3.dispose()
    }
}
