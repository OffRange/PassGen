package de.davis.passgen

import de.davis.passgen.characterset.CharacterSet
import de.davis.passgen.password.GeneratedPassword
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

/**
 * DSL-driven password generator.
 * @property random The random number generator to use for password generation.
 * Defaults to a secure random number generator.
 */
@PasswordGeneratorDsl
class PasswordGenerator(private val random: Random = SecureRandom().asKotlinRandom()) {

    private var length: Int = 8
    private var characterSet: MutableSet<CharacterSet> = mutableSetOf()


    /**
     * Sets the length of the generated password.
     * @param length The length of the password.
     */
    fun length(length: Int) {
        this.length = length
    }

    /**
     * Adds lowercase letters to the character set.
     */
    fun lowercase() {
        characterSet += CharacterSet.Lower
    }

    /**
     * Adds uppercase letters to the character set.
     */
    fun uppercase() {
        characterSet += CharacterSet.Upper
    }

    /**
     * Adds digits to the character set.
     */
    fun digits() {
        characterSet += CharacterSet.Digits
    }

    /**
     * Adds punctuation characters to the character set.
     */
    fun punctuations() {
        characterSet += CharacterSet.Punctuations
    }

    /**
     * Allows adding a custom character set to the password generator.
     * @param custom A lambda function to customize the character set.
     */
    fun custom(custom: (MutableSet<CharacterSet>) -> Unit) {
        custom(characterSet)
    }

    /**
     * Adds lowercase letters, uppercase letters, digits, and punctuations to the character set.
     */
    fun alphanumeric() {
        lowercase()
        uppercase()
        digits()
        punctuations()
    }

    /**
     * Generates a password based on the configured settings.
     * @return The generated password along with the used character sets.
     */
    fun generate(): GeneratedPassword {
        val generated = (1..length).map {
            val charSet = characterSet.random(random)
            charSet to charSet.getChars().random(random)
        }

        val pwd = generated.map { it.second }.joinToString("")

        return GeneratedPassword(pwd, generated.groupBy { it.first }.keys)
    }
}

/**
 * DSL function to generate a password using the PasswordGenerator DSL.
 *
 * @param init The lambda function used to configure the password generator.
 * Within this lambda, you can specify desired characteristics of the generated password
 * using functions like `lowercase()`, `uppercase()`, `digits()`, `punctuations()`, `custom()`, or `alphanumeric()`.
 *
 * @return A [GeneratedPassword] object representing the generated password along with the set of character sets used in its generation.
 */
@PasswordGeneratorDsl
fun generatePassword(init: PasswordGenerator.() -> Unit): GeneratedPassword {
    val generator = PasswordGenerator()
    generator.init()
    return generator.generate()
}