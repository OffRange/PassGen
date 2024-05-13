package de.davis.passgen.pool

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GenerationPoolTest {

    @Test
    fun `plus operator should combine two pools`() {
        val pool1 = ('0'..'5').toCharPool()
        val pool2 = ('6'..'9').toCharPool()
        val combinedPool = pool1 + pool2

        ('0'..'9').forEach { assertTrue(it in combinedPool, "Combined pool should contain all elements") }
    }

    @Test
    fun `get operator should retrieve the element at the specified index`() {
        val pool = ('1'..'5').toCharPool()

        assertEquals('1', pool[0], "Incorrect element at index 0")
        assertEquals('3', pool[2], "Incorrect element at index 2")
        assertEquals('5', pool[4], "Incorrect element at index 4")
    }

    @Test
    fun `contains operator should check if an element or iterable is in the pool`() {
        val pool = ('1'..'5').toCharPool()

        assertTrue('3' in pool, "'3' should be in the pool")
        assertTrue(listOf('1', '4') in pool, "['1', '4'] should be in the pool")
        assertFalse('6' in pool, "'6' should not be in the pool")
        assertFalse(listOf('1', '6') in pool, "['1', '6'] should not be in the pool")
    }

    @Test
    fun `equals operator should compare two pools`() {
        val pool1 = ('1'..'5').toCharPool()
        val pool2 = ('1'..'5').toCharPool()
        val pool3 = ('2'..'6').toCharPool()

        assertTrue(pool1 == pool2, "Identical pools should be equal")
        assertFalse(pool1 == pool3, "Different pools should not be equal")
    }

    @Test
    fun `companion object pools should be correct`() {
        val lowercasePool = GenerationPool.Lowercase
        val uppercasePool = GenerationPool.Uppercase
        val digitsPool = GenerationPool.Digits
        val punctuationsPool = GenerationPool.Punctuations

        ('a'..'z').forEach { assertTrue(it in lowercasePool, "Lowercase pool should contain $it") }
        ('A'..'Z').forEach { assertTrue(it in uppercasePool, "Uppercase pool should contain $it") }
        ('0'..'9').forEach { assertTrue(it in digitsPool, "Digits pool should contain $it") }
        "!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~".forEach {
            assertTrue(
                it in punctuationsPool,
                "Punctuations pool should contain $it"
            )
        }
    }

    @Test
    fun `fromString should create a CharPool from a string`() {
        val charPool = GenerationPool.fromString("abc123!@#")

        listOf('a', 'b', 'c', '1', '2', '3', '!', '@', '#').forEach {
            assertTrue(
                it in charPool,
                "CharPool should contain $it"
            )
        }
    }

    @Test
    fun `fromCharRange should create a CharPool from a CharRange`() {
        val charPool = GenerationPool.fromCharRange('a'..'z')

        ('a'..'z').forEach { assertTrue(it in charPool, "CharPool should contain $it") }
    }

    @Test
    fun `fromCharRange should create a CharPool from start and end chars`() {
        val charPool = GenerationPool.fromCharRange('0', '9')

        ('0'..'9').forEach { assertTrue(it in charPool, "CharPool should contain $it") }
    }

    @Test
    fun `fromStrings should create a StringPool from an array of strings`() {
        val stringPool = GenerationPool.fromStrings("abc", "def", "ghi")

        listOf("abc", "def", "ghi").forEach { assertTrue(it in stringPool, "StringPool should contain $it") }
    }


    @Test
    fun `String toCharPool should create a CharPool from a string`() {
        val charPool = "abc123!@#".toCharPool()

        listOf('a', 'b', 'c', '1', '2', '3', '!', '@', '#').forEach {
            assertTrue(
                charPool.contains(it),
                "CharPool should contain $it"
            )
        }
    }

    @Test
    fun `CharRange toCharPool should create a CharPool from a CharRange`() {
        val charPool = ('a'..'z').toCharPool()

        ('a'..'z').forEach { assertTrue(charPool.contains(it), "CharPool should contain $it") }
    }

    @Test
    fun `Iterable toStringPool should create a StringPool from an Iterable of strings`() {
        val stringPool = listOf("abc", "def", "ghi").toStringPool()

        listOf("abc", "def", "ghi").forEach { assertTrue(stringPool.contains(it), "StringPool should contain $it") }
    }

    @Test
    fun `Array toStringPool should create a StringPool from an Array of strings`() {
        val stringPool = arrayOf("abc", "def", "ghi").toStringPool()

        listOf("abc", "def", "ghi").forEach { assertTrue(stringPool.contains(it), "StringPool should contain $it") }
    }
}