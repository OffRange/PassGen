package de.davis.passgen.configs

import de.davis.passgen.pool.GenerationPool.Companion.Digits
import de.davis.passgen.pool.GenerationPool.Companion.Lowercase
import de.davis.passgen.pool.GenerationPool.Companion.Punctuations
import de.davis.passgen.pool.GenerationPool.Companion.Uppercase
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordGeneratorConfigTest {

    @Test
    fun `configure password generator with custom settings`() {
        val config = PasswordGeneratorConfig().apply {
            custom(Lowercase)
            custom(Uppercase)
            digits()
            punctuations()
            poolsEvenlyDistributed = true
        }

        assertTrue(config.pools.contains(Lowercase))
        assertTrue(config.pools.contains(Uppercase))
        assertTrue(config.pools.contains(Digits))
        assertTrue(config.pools.contains(Punctuations))
        assertTrue(config.poolsEvenlyDistributed)
    }

    @Test
    fun `configure password generator with alphanumeric settings`() {
        val config = PasswordGeneratorConfig().apply {
            alphanumeric()
            poolsEvenlyDistributed = true
        }

        assertTrue(config.pools.contains(Lowercase))
        assertTrue(config.pools.contains(Uppercase))
        assertTrue(config.pools.contains(Digits))
        assertTrue(config.pools.contains(Punctuations))
        assertTrue(config.poolsEvenlyDistributed)
    }

    @Test
    fun `configure password generator with lowercase and digits settings`() {
        val config = PasswordGeneratorConfig().apply {
            lowercase()
            digits()
            poolsEvenlyDistributed = true
        }

        assertTrue(config.pools.contains(Lowercase))
        assertTrue(config.pools.contains(Digits))
        assertFalse(config.pools.contains(Uppercase))
        assertFalse(config.pools.contains(Punctuations))
        assertTrue(config.poolsEvenlyDistributed)
    }
}
