package com.jgpl

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.jgpl.screen.gameplay.GamePlayScreen
import com.jgpl.screen.start.StartScreen
import com.jgpl.utils.UpdateScreen

class Game : ApplicationAdapter() {

    private lateinit var currentScreen: Screen

    override fun create() {
        currentScreen = StartScreen {
            currentScreen.dispose()
            currentScreen = GamePlayScreen()
            currentScreen.show()
        }
        currentScreen.show()
    }

    override fun render() {
        (currentScreen as UpdateScreen).update(Gdx.graphics.deltaTime)
        currentScreen.render(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        currentScreen.dispose()
    }

}

sealed class Difficult(val speed: Float) {
    object Easy : Difficult(0.25f)
    object Normal : Difficult(0.10f)
    object Hard : Difficult(0.05f)
    object Extreme : Difficult(0.025f)
}
