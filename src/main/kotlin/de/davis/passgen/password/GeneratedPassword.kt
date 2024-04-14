package de.davis.passgen.password

import de.davis.passgen.characterset.CharacterSet

data class GeneratedPassword(val value: String, val usedCharacterSets: Set<CharacterSet>) {
    val length = value.length
}