package de.davis.passgen.pool

import kotlin.random.Random

/**
 * Represents a pool of elements used for generation.
 *
 * @param GenerationType The type of elements stored in the pool.
 * @property poolSet The set of elements in the pool.
 */
open class GenerationPool<GenerationType>(private var poolSet: Set<GenerationType> = emptySet()) {

    /**
     * Shuffles the elements in the pool.
     *
     * @param random The [Random] instance to use for shuffling. Default is a new instance of [Random].
     */
    fun shuffle(random: Random = Random) {
        poolSet = poolSet.shuffled(random).toSet()
    }

    /**
     * Retrieves a random element from the pool.
     *
     * @param random The [Random] instance to use for randomness. Default is a new instance of [Random].
     * @return A randomly selected element from the pool.
     */
    fun random(random: Random = Random): GenerationType = poolSet.random(random)

    /**
     * Combines two pools into a single pool.
     *
     * @param pool The pool to combine with this pool.
     * @return A new pool containing the elements from both pools.
     */
    operator fun plus(pool: GenerationPool<GenerationType>) = GenerationPool(pool.poolSet + poolSet)

    /**
     * Retrieves the element at the specified index in the pool.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    operator fun get(index: Int) = poolSet.elementAt(index)

    /**
     * Checks if all elements in the specified iterable are present in the pool.
     *
     * @param poolType The iterable to check against the pool.
     * @return `true` if all elements in the iterable are present in the pool, `false` otherwise.
     */
    operator fun contains(poolType: Iterable<GenerationType>) = poolType.all { it in poolSet }

    /**
     * Checks if the specified element is present in the pool.
     *
     * @param poolType The element to check for in the pool.
     * @return `true` if the element is present in the pool, `false` otherwise.
     */
    operator fun contains(poolType: GenerationType) = poolType in poolSet

    /**
     * Checks if all elements in the specified pool are present in this pool.
     *
     * @param pool The pool to check against this pool.
     * @return `true` if all elements in the specified pool are present in this pool, `false` otherwise.
     */
    operator fun contains(pool: GenerationPool<GenerationType>) = pool.poolSet in this

    override operator fun equals(other: Any?): Boolean {
        other ?: return false
        if (other !is GenerationPool<*>) return false

        return poolSet.all { other.poolSet.contains(it) }
    }

    override fun toString(): String = "GenerationPool{poolSet=$poolSet}"

    override fun hashCode(): Int {
        return poolSet.hashCode()
    }

    companion object {

        /**
         * A pre-defined pool containing lowercase letters.
         */
        val Lowercase = fromCharRange('a', 'z')

        /**
         * A pre-defined pool containing uppercase letters.
         */
        val Uppercase = fromCharRange('A', 'Z')

        /**
         * A pre-defined pool containing digits.
         */
        val Digits = fromCharRange('0', '9')

        /**
         * A pre-defined pool containing punctuation characters.
         */
        val Punctuations = "!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharPool()


        /**
         * Creates a character pool from the specified string.
         *
         * @param string The string to create the character pool from.
         * @return A character pool containing the characters from the string.
         */
        @JvmStatic
        fun fromString(string: String) = string.toCharPool()

        /**
         * Creates a character pool from the specified character range.
         *
         * @param range The character range to create the character pool from.
         * @return A character pool containing the characters in the range.
         */
        @JvmStatic
        fun fromCharRange(range: CharRange) = range.toCharPool()

        /**
         * Creates a character pool from the specified range of characters.
         *
         * @param start The start character of the range.
         * @param end The end character of the range.
         * @return A character pool containing the characters in the specified range.
         */
        @JvmStatic
        fun fromCharRange(start: Char, end: Char) = (start..end).toCharPool()

        /**
         * Creates a string pool from the specified strings.
         *
         * @param strings The strings to create the string pool from.
         * @return A string pool containing the specified strings.
         */
        @JvmStatic
        fun fromStrings(vararg strings: String) = StringPool(strings.toSet())

    }
}

/**
 * Represents a pool of strings used for generation.
 *
 * @param poolSet The set of strings in the pool.
 */
class StringPool(poolSet: Set<String>) : GenerationPool<String>(poolSet)

/**
 * Represents a pool of characters used for generation.
 *
 * @param poolSet The set of characters in the pool.
 */
class CharPool(poolSet: Set<Char>) : GenerationPool<Char>(poolSet)

/**
 * Converts a string to a character pool.
 *
 * @return A character pool containing the characters from the string.
 */
fun String.toCharPool() = CharPool(toCharArray().toSet())

/**
 * Converts a character range to a character pool.
 *
 * @return A character pool containing the characters in the range.
 */
fun CharRange.toCharPool() = CharPool(toSet())

/**
 * Converts an iterable of strings to a string pool.
 *
 * @return A string pool containing the strings from the iterable.
 */
fun Iterable<String>.toStringPool() = StringPool(toSet())

/**
 * Converts an array of strings to a string pool.
 *
 * @return A string pool containing the strings from the array.
 */
fun Array<String>.toStringPool() = StringPool(toSet())
