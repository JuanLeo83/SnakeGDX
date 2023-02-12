package com.jgpl.entity.displaytext

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.*

class LanguageDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.LanguageOption("").get(language).fontSize
            borderWidth = 5f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(option: String) {
        val text = GameText.LanguageOption(option).get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            80f
        )
    }

}
