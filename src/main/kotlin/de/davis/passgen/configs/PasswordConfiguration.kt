package de.davis.passgen.configs

import de.davis.passgen.markers.ConfigDsl
import de.davis.passgen.pool.GenerationPool

/**
 * Represents a configuration for generating passwords.
 * Inherits from [GeneratorConfiguration] with elements of type [Char].
 */
@ConfigDsl
class PasswordGeneratorConfig : GeneratorConfiguration<Char>() {

    /**
     * Determines whether character pools are evenly distributed. Default value is false.
     */
    @ConfigDsl
    var poolsEvenlyDistributed = false
}

/**
 * Adds lowercase characters to the character pools configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.lowercase() = +GenerationPool.Lowercase

/**
 * Adds uppercase characters to the character pools configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.uppercase() = +GenerationPool.Uppercase

/**
 * Adds digit characters to the character pools configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.digits() = +GenerationPool.Digits

/**
 * Adds punctuation characters to the character pools configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.punctuations() = +GenerationPool.Punctuations

/**
 * Adds alphanumeric characters (lowercase, uppercase, digits, punctuations) to the character pools configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.alphanumeric() {
    lowercase()
    uppercase()
    digits()
    punctuations()
}
