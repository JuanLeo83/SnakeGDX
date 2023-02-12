package com.jgpl.utils

import com.badlogic.gdx.graphics.Color

sealed class GameColor(
    private val red: Float,
    private val green: Float,
    private val blue: Float,
    private val alpha: Float
) {
    object Pink : GameColor(255 / 255f, 0 / 255f, 252 / 255f, 1f)
    object Indigo : GameColor(46 / 255f, 0 / 255f, 159 / 255f, 1f)
    object SkyBlue : GameColor(0 / 255f, 217 / 255f, 255 / 255f, 1f)

    object TransParentBlack : GameColor(0f, 0f, 0f, 0.5f)

    fun toGdxColor(): Color = Color(red, green, blue, alpha)
}
