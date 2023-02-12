package com.jgpl.utils

sealed class GameText(
    val en: LanguageText,
    val es: LanguageText
) {
    object GameOver : GameText(
        en = LanguageText("Game over", 110, 100),
        es = LanguageText("Fin de partida", 55, 90)
    )

    object Pause : GameText(
        en = LanguageText("Pause", 230, 100),
        es = LanguageText("Pausa", 230, 100)
    )

    object RestartMessage : GameText(
        en = LanguageText("Press space key to restart", 190, 30),
        es = LanguageText("Presiona espacio para reiniciar", 150, 30)
    )

    data class Score(val score: Int) : GameText(
        en = LanguageText("Score: $score", 10, 30),
        es = LanguageText("Puntos: $score", 10, 30)
    )

    fun get(language: Language): LanguageText {
        return when (language) {
            Language.English -> en
            Language.Spanish -> es
        }
    }
}

data class LanguageText(
    val text: String,
    val horizontalPosition: Int,
    val fontSize: Int
)

sealed interface Language {
    object English : Language
    object Spanish : Language
}
