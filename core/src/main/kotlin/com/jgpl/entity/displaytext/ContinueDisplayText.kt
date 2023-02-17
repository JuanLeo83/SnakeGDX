package com.jgpl.entity.displaytext

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class ContinueDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.Continue.get(language).fontSize
            borderWidth = 3f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(show: Boolean) {
        if (!show) return

        val text = GameText.Continue.get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            180f
        )
    }

}
