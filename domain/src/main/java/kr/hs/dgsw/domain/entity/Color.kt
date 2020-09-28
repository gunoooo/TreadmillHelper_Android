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
            RED -> "#FF0000"
            ORANGE -> "#FF8000"
            YELLOW -> "#FFFF00"
            GREEN -> "#00CC66"
            BLUE -> "#0000CC"
            PURPLE -> "#6600CC"
            BLACK -> "#000000"
            NONE -> "#00FFFFFF"
        }
    }
}