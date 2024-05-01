package de.davis.passgen.configs

import de.davis.passgen.characterset.CharacterSet
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordGeneratorConfigTest {

    @Test
    fun `configure password generator with custom settings`() {
        val config = PasswordGeneratorConfig().apply {
            custom(CharacterSet.Lowercase)
            custom(CharacterSet.Uppercase)
            digits()
            punctuations()
            charSetsEvenlyDistributed = true
        }

        assertTrue(config.characterSets.contains(CharacterSet.Lowercase))
        assertTrue(config.characterSets.contains(CharacterSet.Uppercase))
        assertTrue(config.characterSets.contains(CharacterSet.Digits))
        assertTrue(config.characterSets.contains(CharacterSet.Punctuations))
        assertTrue(config.charSetsEvenlyDistributed)
    }

    @Test
    fun `configure password generator with alphanumeric settings`() {
        val config = PasswordGeneratorConfig().apply {
            alphanumeric()
            charSetsEvenlyDistributed = true
        }

        assertTrue(config.characterSets.contains(CharacterSet.Lowercase))
        assertTrue(config.characterSets.contains(CharacterSet.Uppercase))
        assertTrue(config.characterSets.contains(CharacterSet.Digits))
        assertTrue(config.characterSets.contains(CharacterSet.Punctuations))
        assertTrue(config.charSetsEvenlyDistributed)
    }

    @Test
    fun `configure password generator with lowercase and digits settings`() {
        val config = PasswordGeneratorConfig().apply {
            lowercase()
            digits()
            charSetsEvenlyDistributed = true
        }

        assertTrue(config.characterSets.contains(CharacterSet.Lowercase))
        assertTrue(config.characterSets.contains(CharacterSet.Digits))
        assertFalse(config.characterSets.contains(CharacterSet.Uppercase))
        assertFalse(config.characterSets.contains(CharacterSet.Punctuations))
        assertTrue(config.charSetsEvenlyDistributed)
    }
}
