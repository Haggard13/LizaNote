package ru.eh13.lizanote.feature.note.data.model

internal enum class StyleDto(val code: String) {
    BOLD("bold"),
    ITALIC("italic"),
    UNDERLINE("underline"),
    STRIKETHROUGH("strikethrough"),
    COLORED("colored"),
    SELECTED("selected");

    companion object {
        fun getByStyle(style: String) =
            StyleDto.entries.find { style.startsWith(it.code, ignoreCase = true) }
    }
}