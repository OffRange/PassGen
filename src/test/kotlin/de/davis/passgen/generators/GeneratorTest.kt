package de.davis.passgen.generators

import de.davis.passgen.characterset.CharacterSet
import de.davis.passgen.configs.GeneratorConfiguration
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GeneratorTest {

    private class TestConfig : GeneratorConfiguration()

    @Suppress("PrivatePropertyName")
    private val TestGenerator = object : Generator<TestConfig>(::TestConfig) {
        override fun generate(config: TestConfig): String = TEST_STRING
    }

    @Test
    fun `generate multiple strings using separate instances`() {
        val result1 = generate(TestGenerator) {
            +CharacterSet.Lowercase
        }
        val result2 = generate(TestGenerator) {
            +CharacterSet.Lowercase
        }

        assertEquals("Generator Test", result1)
        assertEquals("Generator Test", result2)
    }

    companion object {

        private const val TEST_STRING = "Generated String"
    }
}