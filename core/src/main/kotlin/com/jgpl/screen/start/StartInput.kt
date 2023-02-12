package com.jgpl.screen.start

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.jgpl.entity.CursorMovement
import com.jgpl.entity.Direction
import kotlin.random.Random

class StartInput {

    fun getMove(): CursorMovement {
        return if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            CursorMovement.Up
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            CursorMovement.Down
        } else {
            CursorMovement.None
        }
    }

    fun nextOption(): Boolean = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

    fun getRandomDirection(lastDirection: Direction): Direction {
        return when (Random.nextInt(0, 4)) {
            0 -> if (lastDirection != Direction.Down) {
                Direction.Up
            } else lastDirection

            1 -> if (lastDirection != Direction.Left) {
                Direction.Right
            } else lastDirection

            2 -> if (lastDirection != Direction.Up) {
                Direction.Down
            } else lastDirection

            3 -> if (lastDirection != Direction.Right) {
                Direction.Left
            } else lastDirection

            else -> lastDirection
        }
    }

}
