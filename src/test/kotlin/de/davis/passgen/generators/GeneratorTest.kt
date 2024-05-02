package de.davis.passgen.generators

import de.davis.passgen.configs.GeneratorConfiguration
import de.davis.passgen.configs.lowercase
import de.davis.passgen.pool.GenerationPool.Companion.Lowercase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class GeneratorTest {

    private class TestConfig : GeneratorConfiguration<Char>()

    @Suppress("PrivatePropertyName")
    private val TestGenerator = object : Generator<Char, TestConfig>(::TestConfig) {
        override fun generate(config: TestConfig): String = TEST_STRING
    }

    @Test
    fun `generate multiple strings using separate instances`() {
        val result1 = generate(TestGenerator) {
            +Lowercase
        }
        val result2 = generate(TestGenerator) {
            +Lowercase
        }

        assertEquals(TEST_STRING, result1)
        assertEquals(TEST_STRING, result2)
    }

    @Test
    fun `generate with invalid length throws exception`() {
        generate(Password) {
            lowercase()
            assertThrows<IllegalArgumentException> {
                length(0)
            }
        }
    }

    companion object {

        private const val TEST_STRING = "Generated String"
    }
}