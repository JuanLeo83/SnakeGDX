package com.jgpl

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.jgpl.screen.gameplay.GamePlayScreen

class Game : ApplicationAdapter() {

    private val difficult = Difficult.Hard

    private lateinit var gamePlayScreen: GamePlayScreen

    override fun create() {
        gamePlayScreen = GamePlayScreen(difficult)
        gamePlayScreen.show()
    }

    override fun render() {
        gamePlayScreen.update(Gdx.graphics.deltaTime)
        gamePlayScreen.render(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        gamePlayScreen.dispose()
    }

}

sealed class Difficult(val speed: Float) {
    object Easy : Difficult(0.25f)
    object Normal : Difficult(0.10f)
    object Hard : Difficult(0.05f)
    object Extreme : Difficult(0.025f)
}
