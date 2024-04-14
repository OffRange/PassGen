package de.davis.passgen.password

import de.davis.passgen.characterset.CharacterSet

/**
 * Represents a generated password along with the set of character sets used to generate it.
 * @property value The generated password string.
 * @property usedCharacterSets The set of character sets used to generate the password.
 */
data class GeneratedPassword(
    val value: String,
    val usedCharacterSets: Set<CharacterSet>
) {
    /**
     * Returns the length of the generated password.
     */
    val length = value.length
}