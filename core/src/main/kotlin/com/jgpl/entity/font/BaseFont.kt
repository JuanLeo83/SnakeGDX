package com.jgpl.entity.font

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

abstract class BaseFont(
    private val spriteBatch: SpriteBatch,
    fontName: String
) {

    protected val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("fonts/$fontName"))
    protected val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()

    protected lateinit var font: BitmapFont

    fun render(text: String, positionX: Float, positionY: Float) {
        font.draw(spriteBatch, text, positionX, positionY)
    }

    open fun dispose() {
        fontGenerator.dispose()
        font.dispose()
    }

}
