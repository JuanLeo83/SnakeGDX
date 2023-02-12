package com.jgpl.screen.start

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.jgpl.entity.CursorMovement

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

}
