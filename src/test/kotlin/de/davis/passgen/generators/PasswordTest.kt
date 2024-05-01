package de.davis.passgen.generators

import de.davis.passgen.configs.lowercase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordTest {

    @Test
    fun `generate password with specified length`() {
        val length = 8
        val pwd = generate(Password) {
            length(length)
            lowercase()
        }

        assert(pwd.length == length)
    }

    @Test
    fun `generate password with invalid length throws exception`() {
        generate(Password) {
            assertThrows<IllegalArgumentException> {
                length(0)
            }
        }
    }
}