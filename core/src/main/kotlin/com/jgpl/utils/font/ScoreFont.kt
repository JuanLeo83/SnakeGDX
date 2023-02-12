package com.jgpl.utils.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor

class ScoreFont(
    spriteBatch: SpriteBatch
) : BaseFont(spriteBatch, "Kanit-Medium.ttf") {

    init {
        parameter.apply {
            size = 30
            borderWidth = 3f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(score: Int) {
        super.render("Score: $score", 10f, (Gdx.graphics.height - 15).toFloat())
    }

}
