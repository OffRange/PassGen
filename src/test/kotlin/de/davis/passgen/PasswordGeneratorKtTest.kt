package de.davis.passgen

import de.davis.passgen.characterset.CharacterSet.Custom
import de.davis.passgen.test.assertContainsOnly
import org.junit.jupiter.api.Test

class PasswordGeneratorKtTestGenerate {

    @Test
    fun `generate password - length`() {
        val length = 8
        val pwd = generatePassword {
            length(length)
            lowercase()
        }

        assert(pwd.length == length)
    }

    @Test
    fun `generate password - numeric`() {
        testGenerate {
            digits()
        }
    }

    @Test
    fun `generate password - alphabetical lower`() {
        testGenerate {
            lowercase()
        }
    }

    @Test
    fun `generate password - alphabetical upper`() {
        testGenerate {
            uppercase()
        }
    }

    @Test
    fun `generate password - alphabetical`() {
        testGenerate {
            lowercase()
            uppercase()
        }
    }

    @Test
    fun `generate password - punctuational`() {
        testGenerate {
            punctuations()
        }
    }

    @Test
    fun `generate password - alphanumeric`() {
        testGenerate {
            alphanumeric()
        }
    }

    @Test
    fun `generate password - custom`() {
        val customSet = Custom("Q")
        testGenerate {
            custom {
                it.add(customSet)
            }
        }
    }

    fun testGenerate(initiate: PasswordGenerator.() -> Unit) {
        val password = generatePassword {
            initiate()
        }

        assertContainsOnly(password.value, password.usedCharacterSets)
    }
}