package de.davis.passgen.generators

import de.davis.passgen.pool.GenerationPool
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PassphraseTest {

    @Test
    fun `generate passphrase with specified length`() {
        val length = 2
        val separator = "-"
        val pwd = generate(Passphrase) {
            length(length)
            this.separator = separator
            custom(GenerationPool.fromStrings("Test"))
        }

        assertEquals(pwd.count { it == '-' }, length - 1)
    }
}