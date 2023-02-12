package com.jgpl.entity.displaytext

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jgpl.utils.GameColor
import com.jgpl.utils.GameText
import com.jgpl.utils.fontKanit
import com.jgpl.utils.language

class PlayDisplayText(
    spriteBatch: SpriteBatch
) : BaseDisplayText(spriteBatch, fontKanit) {

    init {
        parameter.apply {
            size = GameText.Play.get(language).fontSize
            borderWidth = 5f
            borderColor = GameColor.Pink.toGdxColor()
            color = Color.WHITE
        }

        font = fontGenerator.generateFont(parameter)
    }

    fun render() {
        val text = GameText.Play.get(language)

        super.render(
            text.text,
            text.horizontalPosition.toFloat(),
            180f
        )
    }

}
