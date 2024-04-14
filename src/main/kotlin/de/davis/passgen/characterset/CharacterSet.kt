package de.davis.passgen.characterset

/**
 * Interface representing a character set used in password generation.
 */
sealed interface CharacterSet {

    /**
     * Retrieves the characters belonging to the character set.
     * @return A string containing the characters of the set.
     */
    fun getChars(): String

    /**
     * Represents the set of lowercase letters (a-z).
     */
    data object Lower : CharacterSet {
        override fun getChars(): String = ('a'..'z').joinToString("")
    }

    /**
     * Represents the set of uppercase letters (A-Z).
     */
    data object Upper : CharacterSet {
        override fun getChars(): String = ('A'..'Z').joinToString("")
    }

    /**
     * Represents the set of digits (0-9).
     */
    data object Digits : CharacterSet {
        override fun getChars(): String = ('0'..'9').joinToString("")
    }

    /**
     * Represents a set of common punctuation characters.
     */
    data object Punctuations : CharacterSet {
        override fun getChars(): String = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
    }

    /**
     * Represents a custom character set defined by a specific string of characters.
     * @property characters The custom characters for the set.
     */
    data class Custom(val characters: String) : CharacterSet {
        override fun getChars(): String = characters
    }
}