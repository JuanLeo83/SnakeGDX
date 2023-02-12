package com.jgpl.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jgpl.utils.GameColor

class ScoreArea {

    private val shapeRenderer = ShapeRenderer()

    fun render() {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            color = GameColor.DarkIndigo.toGdxColor()

            rect(
                0f,
                Gdx.graphics.height - 50f,
                Gdx.graphics.width.toFloat(),
                50f
            )

            color = Color.WHITE
            rect(0f, Gdx.graphics.height - 50f, Gdx.graphics.width.toFloat(), 1f)

            end()
        }
    }

    fun dispose() {
        shapeRenderer.dispose()
    }
}
