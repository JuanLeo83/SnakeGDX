package com.jgpl.screen.gameplay

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.jgpl.entity.Direction

class GamePlayInput {

    fun getDirection(lastDirection: Direction): Direction {
        return if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (lastDirection != Direction.Down) {
                Direction.Up
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (lastDirection != Direction.Left) {
                Direction.Right
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (lastDirection != Direction.Up) {
                Direction.Down
            } else lastDirection
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (lastDirection != Direction.Right) {
                Direction.Left
            } else lastDirection
        } else {
            lastDirection
        }
    }

    fun getStart(): Boolean = Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)

    fun getRestart(): Boolean = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

    fun getPause(): Boolean = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)

}
