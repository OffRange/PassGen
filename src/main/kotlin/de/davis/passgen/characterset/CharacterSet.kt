package de.davis.passgen.characterset

sealed interface CharacterSet {

    fun getChars(): String

    data object Lower : CharacterSet {
        override fun getChars(): String = ('a'..'z').joinToString("")
    }

    data object Upper : CharacterSet {
        override fun getChars(): String = ('A'..'Z').joinToString("")
    }

    data object Digits : CharacterSet {
        override fun getChars(): String = ('0'..'9').joinToString("")
    }

    data object Punctuations : CharacterSet {
        override fun getChars(): String = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
    }

    data class Custom(val characters: String) : CharacterSet {
        override fun getChars(): String = characters
    }
}