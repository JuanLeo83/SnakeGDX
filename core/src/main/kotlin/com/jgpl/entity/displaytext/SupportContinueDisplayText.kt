package com.jgpl.entity.displaytext

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class SupportContinueDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.SupportContinueOptionsText.get(language).fontSize
            borderWidth = 0f
            color = GameColor.Pink.toGdxColor()
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(show: Boolean) {
        if (!show) return

        val text = GameText.SupportContinueOptionsText.get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            15f
        )
    }

}
