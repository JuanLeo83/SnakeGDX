package com.jgpl.utils

sealed class GameText(
    val en: LanguageText,
    val es: LanguageText
) {
    object Play : GameText(
        en = LanguageText("Play", 100, 30),
        es = LanguageText("Jugar", 100, 30)
    )

    data class Difficult(val option: String) : GameText(
        en = LanguageText("Difficult:   $option", 100, 30),
        es = LanguageText("Dificultad:   $option", 100, 30)
    )

    object Easy : GameText(
        en = LanguageText("Easy", 30, 30),
        es = LanguageText("Fácil", 30, 30)
    )

    object Normal : GameText(
        en = LanguageText("Normal", 30, 30),
        es = LanguageText("Normal", 30, 30)
    )

    object Hard : GameText(
        en = LanguageText("Hard", 30, 30),
        es = LanguageText("Difícil", 30, 30)
    )

    object Extreme : GameText(
        en = LanguageText("Extreme", 30, 30),
        es = LanguageText("Extremo", 30, 30)
    )

    data class LanguageOption(val option: String) : GameText(
        en = LanguageText("Language:   $option", 100, 30),
        es = LanguageText("Idioma:   $option", 100, 30)
    )

    object English : GameText(
        en = LanguageText("English", 30, 30),
        es = LanguageText("Inglés", 30, 30)
    )

    object Spanish : GameText(
        en = LanguageText("Spanish", 30, 30),
        es = LanguageText("Español", 30, 30)
    )

    object GameOver : GameText(
        en = LanguageText("Game over", 110, 100),
        es = LanguageText("Fin de partida", 55, 90)
    )

    object Pause : GameText(
        en = LanguageText("Pause", 230, 100),
        es = LanguageText("Pausa", 230, 100)
    )

    object NewGameMessage : GameText(
        en = LanguageText("Press space key to restart", 190, 30),
        es = LanguageText("Presiona espacio para reiniciar", 150, 30)
    )

    data class Score(val score: Int) : GameText(
        en = LanguageText("Score: $score", 10, 30),
        es = LanguageText("Puntos: $score", 10, 30)
    )

    object PressAnyKey : GameText(
        en = LanguageText("Press any key", 290, 30),
        es = LanguageText("Pulsa cualquier tecla", 230, 30)
    )

    object SupportMenuOptionsText : GameText(
        en = LanguageText("Press space to change option", 10, 15),
        es = LanguageText("Presiona espacio para cambiar de opción", 10, 15)
    )

    object SupportMenuStartText : GameText(
        en = LanguageText("Press space to start", 10, 15),
        es = LanguageText("Presiona espacio para empezar", 10, 15)
    )

    object Continue : GameText(
        en = LanguageText("Continue", 100, 30),
        es = LanguageText("Continuar", 100, 30)
    )

    object Restart : GameText(
        en = LanguageText("Restart", 100, 30),
        es = LanguageText("Reiniciar", 100, 30)
    )

    object SupportRestartOptionsText : GameText(
        en = LanguageText("Press space to restart", 10, 15),
        es = LanguageText("Presiona espacio para reiniciar", 10, 15)
    )

    object SupportContinueOptionsText : GameText(
        en = LanguageText("Press space to continue", 10, 15),
        es = LanguageText("Presiona espacio para continuar", 10, 15)
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
