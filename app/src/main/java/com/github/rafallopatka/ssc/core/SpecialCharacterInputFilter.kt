package com.github.rafallopatka.ssc.core

class SpecialCharacterInputFilter : RegexInputFilter(SPECIAL_CHARACTER_REGEX) {
    companion object {

        private val SPECIAL_CHARACTER_REGEX = "[0-9-,.]+"
    }
}