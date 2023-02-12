package com.jgpl.entity.displaytext

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class DifficultDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.Difficult("").get(language).fontSize
            borderWidth = 5f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render(option: String) {
        val text = GameText.Difficult(option).get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            130f
        )
    }

}
