package com.jgpl.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jgpl.utils.GameColor

class Cursor(
    private val onMove: (Int) -> Unit
) {

    private val jump = 50f

    private val shapeRenderer = ShapeRenderer()

    private var cursorPosition = 3
    private var lastPosition = 3

    private var position = 170f

    fun render() {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            translate(60f, position, 0f)

            color = GameColor.Pink.toGdxColor()
            triangle(0f - 15f, 0f - 13f, 0 - 15f, 0f + 13f, 0 + 15f, 0f)

            end()
        }
    }

    fun move(movement: CursorMovement) {
        when (movement) {
            CursorMovement.Up -> moveUp()
            CursorMovement.Down -> moveDown()
            else -> {}
        }
        onMove(cursorPosition)
    }

    private fun moveUp() {
        if (cursorPosition == lastPosition) return

        cursorPosition++
        position += jump
    }

    private fun moveDown() {
        if (cursorPosition == 1) return

        cursorPosition--
        position -= jump
    }

    fun dispose() {
        shapeRenderer.dispose()
    }

}

sealed interface CursorMovement {
    object Up : CursorMovement
    object Down : CursorMovement
    object None : CursorMovement
}
