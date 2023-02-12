package com.jgpl.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jgpl.utils.GameColor

class DarkLayer() {

    private val shapeRenderer = ShapeRenderer()

    fun render(isGameOver: Boolean) {
        if (!isGameOver) return

        with(shapeRenderer) {
            Gdx.gl.glEnable(GL20.GL_BLEND)
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
            begin(ShapeRenderer.ShapeType.Filled)
            color = GameColor.TransParentBlack.toGdxColor()
            rect(
                0f,
                0f,
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat()
            )
            end()
        }
    }

    fun dispose() {
        shapeRenderer.dispose()
    }
}
