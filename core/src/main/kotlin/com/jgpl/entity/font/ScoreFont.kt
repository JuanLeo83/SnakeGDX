package com.jgpl.entity.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class ScoreFont(
    spriteBatch: SpriteBatch
) : BaseFont(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.Score(0).get(language).fontSize
            borderWidth = 3f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(score: Int) {
        val text = GameText.Score(score).get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            (Gdx.graphics.height - 15).toFloat()
        )
    }

}
