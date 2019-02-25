package com.github.rafallopatka.ssc.core

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

abstract class RegexInputFilter : InputFilter {
    private val filterPattern: Pattern

    constructor(pattern: String) : this(Pattern.compile(pattern)) {}

    constructor(pattern: Pattern?) {
        if (pattern == null) {
            throw IllegalArgumentException("$CLASS_NAME requires a regex.")
        }

        filterPattern = pattern
    }

    override fun filter(
        source: CharSequence, start: Int, end: Int,
        dest: Spanned, dstart: Int, dend: Int
    ): CharSequence? {

        val matcher = filterPattern.matcher(source)
        return if (!matcher.matches()) {
            ""
        } else null

    }

    companion object {
        private val CLASS_NAME = RegexInputFilter::class.java.simpleName
    }
}