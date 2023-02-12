package com.jgpl.utils.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor

class PauseFont(
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

    fun render(paused: Boolean) {
        if (!paused) return

        super.render(
            "Pause",
            230f,
            ((Gdx.graphics.height + font.lineHeight / 2) / 2)
        )
    }

}
