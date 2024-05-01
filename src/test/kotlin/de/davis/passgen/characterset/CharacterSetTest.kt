package de.davis.passgen.characterset

import org.junit.jupiter.api.Test

class CharacterSetTest {

    @Test
    fun plus() {
        val charset1 = CharacterSet.Custom("a")
        val charset2 = CharacterSet.Custom("B")

        assert((charset1 + charset2).characters == "aB")
    }

    @Test
    fun contains() {
        val charset = CharacterSet.Custom("1AaBbCc3§#")
        val charset2 = CharacterSet.Custom(charset.characters)
        charset2.shuffle()

        assert('a' in charset)
        assert("a" in charset)
        assert("1b#" in charset)
        assert(charset2 in charset)
    }
}