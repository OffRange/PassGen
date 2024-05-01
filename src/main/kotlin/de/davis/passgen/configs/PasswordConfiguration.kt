package de.davis.passgen.configs

import de.davis.passgen.characterset.CharacterSet
import de.davis.passgen.markers.ConfigDsl

/**
 * A configuration class for the password generator.
 * This class extends [GeneratorConfiguration] and provides additional properties and functions
 * for configuring the generation of passwords.
 */
@ConfigDsl
class PasswordGeneratorConfig : GeneratorConfiguration() {

    /**
     * Flag indicating whether character sets should be evenly distributed when generating passwords.
     * If set to true, character sets will be evenly distributed; otherwise, they will be randomly selected.
     */
    @ConfigDsl
    var charSetsEvenlyDistributed = false
}

/**
 * Adds a custom character set to the password generator configuration.
 *
 * @param characterSet The custom character set to be added.
 */
@ConfigDsl
fun PasswordGeneratorConfig.custom(characterSet: CharacterSet) = +characterSet

/**
 * Adds the lowercase character set to the password generator configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.lowercase() = +CharacterSet.Lowercase

/**
 * Adds the uppercase character set to the password generator configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.uppercase() = +CharacterSet.Uppercase

/**
 * Adds the digits character set to the password generator configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.digits() = +CharacterSet.Digits

/**
 * Adds the punctuations character set to the password generator configuration.
 */
@ConfigDsl
fun PasswordGeneratorConfig.punctuations() = +CharacterSet.Punctuations

/**
 * Configures the password generator to include alphanumeric characters (lowercase, uppercase, digits, and punctuations).
 */
@ConfigDsl
fun PasswordGeneratorConfig.alphanumeric() {
    lowercase()
    uppercase()
    digits()
    punctuations()
}
