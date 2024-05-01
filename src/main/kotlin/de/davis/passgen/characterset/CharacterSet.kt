package de.davis.passgen.characterset

import kotlin.random.Random

/**
 * Represents a set of characters that can be used for generating strings.
 * This class is sealed, meaning it cannot be extended outside this file.
 *
 * @property characters The string containing the characters in this set.
 */
sealed class CharacterSet(characters: String) {

    /**
     * Constructs a [CharacterSet] from a range of characters.
     *
     * @param start The start character of the range (inclusive).
     * @param end The end character of the range (inclusive).
     */
    constructor(start: Char, end: Char) : this((start..end).joinToString(""))

    /**
     * The string containing the characters in this set.
     */
    var characters = characters
        private set

    /**
     * Shuffles the characters in this set using the provided [Random] instance.
     *
     * @param random The [Random] instance to use for shuffling. If not provided, a default [Random] instance will be used.
     */
    fun shuffle(random: Random = Random) {
        characters = characters.toCharArray().apply { shuffle(random) }.joinToString("")
    }

    /**
     * Returns the characters in this set as a string.
     *
     * @return The string containing the characters in this set.
     * @deprecated Deprecated in favor of the `characters` field.
     */
    @Deprecated("Deprecated in favor of characters field", ReplaceWith("characters"))
    fun getChars(): String = characters

    /**
     * Concatenates this [CharacterSet] with another [CharacterSet] and returns a new [Custom] instance containing the combined characters.
     *
     * @param characterSet The [CharacterSet] to concatenate with this instance.
     * @return A new [Custom] instance containing the combined characters.
     */
    operator fun plus(characterSet: CharacterSet) = Custom(characters + characterSet.characters)

    /**
     * Checks if the provided [Char] is contained in this [CharacterSet].
     *
     * @param char The [Char] to check for containment.
     * @return `true` if the [Char] is contained in this [CharacterSet], `false` otherwise.
     */
    operator fun contains(char: Char) = char in characters

    /**
     * Checks if the provided [CharSequence] is contained in this [CharacterSet].
     *
     * @param charSequence The [CharSequence] to check for containment.
     * @return `true` if all characters in the [CharSequence] are contained in this [CharacterSet], `false` otherwise.
     */
    operator fun contains(charSequence: CharSequence) = charSequence.all { it in characters }

    /**
     * Checks if the provided [CharacterSet] is contained in this [CharacterSet].
     *
     * @param characterSet The [CharacterSet] to check for containment.
     * @return `true` if all characters in the provided [CharacterSet] are contained in this [CharacterSet], `false` otherwise.
     */
    operator fun contains(characterSet: CharacterSet) = characterSet.characters in this

    /**
     * A pre-defined [CharacterSet] containing lowercase letters.
     */
    data object Lowercase : CharacterSet('a', 'z')

    /**
     * A pre-defined [CharacterSet] containing uppercase letters.
     */
    data object Uppercase : CharacterSet('A', 'Z')

    /**
     * A pre-defined [CharacterSet] containing digits.
     */
    data object Digits : CharacterSet('0', '9')

    /**
     * A pre-defined [CharacterSet] containing punctuation characters.
     */
    data object Punctuations : CharacterSet("!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~")

    /**
     * A custom [CharacterSet] that can be created with a provided string of characters.
     *
     * @param characters The string containing the characters for the custom set.
     */
    class Custom(characters: String) : CharacterSet(characters)
}