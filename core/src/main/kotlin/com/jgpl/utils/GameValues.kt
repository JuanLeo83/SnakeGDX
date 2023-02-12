package com.jgpl.utils

import com.jgpl.Difficult

const val cellSize = 10

const val gameSizeX = 80
const val gameSizeY = 80

const val fontKanit = "Kanit-Medium.ttf"

var language: Language = Language.English
fun setLanguage(index: Int) {
    language = when (index) {
        0 -> Language.English
        1 -> Language.Spanish
        else -> Language.English
    }
}

var difficult: Difficult = Difficult.Normal
fun setDifficult(index: Int) {
    difficult = when (index) {
        0 -> Difficult.Easy
        1 -> Difficult.Normal
        2 -> Difficult.Hard
        3 -> Difficult.Extreme
        else -> Difficult.Normal
    }
}
