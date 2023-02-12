package com.jgpl.screen.start

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jgpl.entity.Cursor
import com.jgpl.entity.DarkLayer
import com.jgpl.entity.displaytext.DifficultDisplayText
import com.jgpl.entity.displaytext.LanguageDisplayText
import com.jgpl.entity.displaytext.PlayDisplayText
import com.jgpl.entity.displaytext.TitleDisplayText
import com.jgpl.utils.*

class StartScreen(
    private val play: () -> Unit
) : Screen, UpdateScreen {

    private lateinit var spriteBatch: SpriteBatch
    private lateinit var input: StartInput
    private lateinit var state: StartState
    private lateinit var cursor: Cursor
    private lateinit var darkLayer: DarkLayer

    private lateinit var titleText: TitleDisplayText
    private lateinit var playText: PlayDisplayText
    private lateinit var difficultText: DifficultDisplayText
    private lateinit var languageText: LanguageDisplayText

    private var difficultSelected: Int = 0
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

    override fun show() {
        spriteBatch = SpriteBatch()
        input = StartInput()
        state = StartState()

        darkLayer = DarkLayer()

        titleText = TitleDisplayText(spriteBatch)
        playText = PlayDisplayText(spriteBatch)
        difficultText = DifficultDisplayText(spriteBatch)
        languageText = LanguageDisplayText(spriteBatch)

        cursor = Cursor { optionSelected = it }
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
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(GameColor.Indigo.toGdxColor())

        darkLayer.render(true)
        cursor.render()

        spriteBatch.begin()
        titleText.render()
        playText.render()
        difficultText.render(difficultyOptions[difficultSelected])
        languageText.render(languageOptions[languageSelected])
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
        darkLayer.dispose()
    }
}
