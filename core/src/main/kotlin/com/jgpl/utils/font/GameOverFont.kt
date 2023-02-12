package com.jgpl.utils.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor

class GameOverFont(
    spriteBatch: SpriteBatch
) : BaseFont(spriteBatch, "Kanit-Medium.ttf") {

    init {
        parameter.apply {
            size = 100
            borderWidth = 10f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(show: Boolean) {
        if (!show) return

        super.render(
            "Game over",
            110f,
            ((Gdx.graphics.height + font.lineHeight / 2) / 2)
        )
    }

}
