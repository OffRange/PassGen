package de.davis.passgen.test

import de.davis.passgen.characterset.CharacterSet
import kotlin.test.assertContains

fun assertContainsOnly(input: String, characterSets: Set<CharacterSet>, message: String? = null) {
    val chars = characterSets.map { it.getChars() }.joinToString("")
    input.forEach {
        assertContains(chars, it, message = message)
    }
}