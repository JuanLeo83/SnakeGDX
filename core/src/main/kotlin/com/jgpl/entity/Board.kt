package com.jgpl.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jgpl.utils.GameColor
import com.jgpl.utils.cellSize
import kotlin.random.Random

class Board(
    private val snake: Snake,
    private val onEatFood: () -> Unit
) {

    private val shapeRenderer = ShapeRenderer()

    private var food = Pair(40 * cellSize, 40 * cellSize)

    fun update() {
        if (snake.getHeadPosition().first == food.first && snake.getHeadPosition().second == food.second) {
            snake.addPart()
            addFood()
            onEatFood()
        }
    }

    private fun addFood() {
        val snakePositions = snake.getPositions()
        var snakePart: Pair<Int, Int>?

        do {
            val x = Random.nextInt(0, 80)
            val y = Random.nextInt(0, 80)

            snakePart = snakePositions.find {
                it.first == x && it.second == y
            }

            if (snakePart == null) {
                food = Pair(x * cellSize, y * cellSize)
            }
        } while (snakePart != null)
    }

    fun render() {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            color = GameColor.SkyBlue.toGdxColor()

            rect(
                food.first.toFloat(),
                food.second.toFloat(),
                cellSize.toFloat(),
                cellSize.toFloat()
            )

            end()
        }
    }

    fun dispose() {
        shapeRenderer.dispose()
    }

}
