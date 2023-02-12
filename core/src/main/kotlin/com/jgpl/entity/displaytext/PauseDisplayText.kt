package com.jgpl.entity.displaytext

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class PauseDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.Pause.get(language).fontSize
            borderWidth = 10f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(paused: Boolean) {
        if (!paused) return

        val text = GameText.Pause.get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            ((Gdx.graphics.height + font.lineHeight / 2) / 2)
        )
    }

}
