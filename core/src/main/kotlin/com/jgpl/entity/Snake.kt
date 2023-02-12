package com.jgpl.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jgpl.utils.GameColor

class Snake(
    onCollision: () -> Unit
) {

    private val shapeRenderer = ShapeRenderer()

    private var snakeData = SnakeData(onCollision = onCollision)

    fun move(direction: Direction) {
        snakeData.apply {
            updateParts()
            move(direction)
            checkCollision()
        }
    }

    fun addPart() {
        snakeData.addPart()
    }

    fun getHeadPosition(): Pair<Int, Int> = Pair(snakeData.positionX, snakeData.positionY)

    fun getPositions(): List<Pair<Int, Int>> {
        val positions = mutableListOf<Pair<Int, Int>>()
        snakeData.getParts().forEach {
            positions.add(Pair(it.positionX, it.positionY))
        }
        return positions
    }

    fun render() {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            color = GameColor.Pink.toGdxColor()

            rect(
                snakeData.positionX.toFloat(),
                snakeData.positionY.toFloat(),
                cellSize.toFloat(),
                cellSize.toFloat()
            )

            snakeData.getParts().forEach { part ->
                rect(
                    part.positionX.toFloat(),
                    part.positionY.toFloat(),
                    cellSize.toFloat(),
                    cellSize.toFloat()
                )
            }

            end()
        }
    }

    fun dispose() {
        shapeRenderer.dispose()
    }

}


private data class SnakeData(
    var positionX: Int = (gameSizeX / 2) * cellSize,
    var positionY: Int = 50,
    var currentDirection: Direction = Direction.Up,
    val onCollision: () -> Unit
) {

    private val parts: MutableList<SnakePart> = mutableListOf()

    init {
        parts.add(SnakePart(positionX, positionY - cellSize))
        parts.add(SnakePart(positionX, positionY - cellSize * 2))
    }

    fun updateParts() {
        parts.forEachIndexed { index, part ->
            if (index == 0) {
                part.update(positionX, positionY)
            } else {
                part.update(
                    parts[index - 1].previousPositionX,
                    parts[index - 1].previousPositionY
                )
            }
        }
    }

    fun move(direction: Direction) {
        positionX += direction.x
        positionY += direction.y

        if (positionX > (gameSizeX - 1) * cellSize) {
            positionX = 0
        }
        if (positionX < 0) {
            positionX = (gameSizeX - 1) * cellSize
        }

        if (positionY > (gameSizeY - 1) * cellSize) {
            positionY = 0
        }
        if (positionY < 0) {
            positionY = (gameSizeY - 1) * cellSize
        }
    }

    fun checkCollision() {
        val part = parts.find {
            it.positionX == positionX && it.positionY == positionY
        }

        if (part != null) {
            onCollision()
        }
    }

    fun addPart() {
        val newPart = parts.last().copy(
            positionX = parts.last().previousPositionX,
            positionY = parts.last().previousPositionY
        )
        parts.add(newPart)
    }

    fun getParts(): List<SnakePart> = parts
}

private data class SnakePart(
    var positionX: Int,
    var positionY: Int,
    var previousPositionX: Int = 0,
    var previousPositionY: Int = 0
) {
    fun update(posX: Int, posY: Int) {
        previousPositionX = positionX
        previousPositionY = positionY
        positionX = posX
        positionY = posY
    }
}

sealed class Direction(val x: Int, val y: Int) {
    object Up : Direction(0, cellSize)
    object Right : Direction(cellSize, 0)
    object Down : Direction(0, -cellSize)
    object Left: Direction(-cellSize, 0)
}
