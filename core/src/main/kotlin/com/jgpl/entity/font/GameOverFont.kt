package com.jgpl.entity.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class GameOverFont(
    spriteBatch: SpriteBatch
) : BaseFont(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.GameOver.get(language).fontSize
            borderWidth = 10f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(show: Boolean) {
        if (!show) return

        val text = GameText.GameOver.get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            ((Gdx.graphics.height + font.lineHeight / 2) / 2)
        )
    }

}
