package de.davis.passgen.configs

import de.davis.passgen.characterset.CharacterSet
import de.davis.passgen.markers.ConfigDsl

/**
 * A sealed class representing the configuration for a string generator.
 * This class provides properties and functions for configuring the character sets
 * and length of the generated string.
 */
@ConfigDsl
open class GeneratorConfiguration {

    /**
     * The set of character sets to be used for generating the string.
     */
    private val _characterSets = mutableSetOf<CharacterSet>()
    val characterSets get() = _characterSets.toList()

    /**
     * The desired length of the generated string.
     *
     * @throws IllegalArgumentException If the length is set to a non-positive value.
     */
    @ConfigDsl
    var length = 8
        set(value) {
            if (value <= 0)
                throw IllegalArgumentException("The length must be a positive, non-zero number.")

            field = value
        }

    /**
     * Adds the specified [CharacterSet] to the set of character sets used for generating the string.
     *
     * @receiver The [CharacterSet] to be added.
     * @throws IllegalArgumentException If the specified [CharacterSet] has already been added.
     */
    @ConfigDsl
    operator fun CharacterSet.unaryPlus() = addCharacterSet(this)

    /**
     * Adds the specified [CharacterSet] to the set of character sets used for generating the string.
     *
     * @param characterSet The [CharacterSet] to be added.
     * @throws IllegalArgumentException If the specified [CharacterSet] has already been added.
     */
    @ConfigDsl
    protected fun addCharacterSet(characterSet: CharacterSet) {
        _characterSets.firstOrNull { it == characterSet }?.let {
            throw IllegalArgumentException("There is already a character set for $characterSet")
        }

        _characterSets.add(characterSet)
    }

    /**
     * Sets the desired length of the generated string.
     *
     * @param length The desired length of the generated string.
     */
    @ConfigDsl
    fun length(length: Int) {
        this.length = length
    }
}


/**
 * Verifies the given configuration by checking if it meets a certain condition.
 *
 * This extension function checks if the provided [Config] instance meets a specific condition
 * defined by the implementation. If the condition is not met, it throws an exception with an
 * appropriate error message. If the condition is met, the function returns the same [Config] instance
 * without any modifications.
 *
 * The specific condition to be checked and the error message to be thrown in case of failure should
 * be defined by the implementation of this function for different types of configurations.
 *
 * @receiver The [Config] instance to be verified.
 * @return The same [Config] instance if it meets the defined condition.
 * @throws IllegalArgumentException If the [Config] instance does not meet the defined condition.
 */
fun <Config : GeneratorConfiguration> Config.verified(): Config {
    if (characterSets.isEmpty())
        throw IllegalArgumentException("To generate a password, you must specify at least one character set!")

    return this
}