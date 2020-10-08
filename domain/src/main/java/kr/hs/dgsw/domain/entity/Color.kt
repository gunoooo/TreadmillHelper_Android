package kr.hs.dgsw.domain.entity

enum class Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    BLACK,
    NONE;

    fun toRGB(): String {
        return when(this) {
            RED -> "#D64A4A"
            ORANGE -> "#D6824A"
            YELLOW -> "#D6D44A"
            GREEN -> "#4AD65D"
            BLUE -> "#4A68D6"
            PURPLE -> "#8B4AD6"
            BLACK -> "#000000"
            NONE -> "#00FFFFFF"
        }
    }
}