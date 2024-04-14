package de.davis.passgen

import de.davis.passgen.characterset.CharacterSet
import de.davis.passgen.password.GeneratedPassword
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

@PasswordGeneratorDsl
class PasswordGenerator(private val random: Random = SecureRandom().asKotlinRandom()) {

    private var length: Int = 8
    private var characterSet: MutableSet<CharacterSet> = mutableSetOf()


    fun length(length: Int) {
        this.length = length
    }

    fun lowercase() {
        characterSet += CharacterSet.Lower
    }

    fun uppercase() {
        characterSet += CharacterSet.Upper
    }

    fun digits() {
        characterSet += CharacterSet.Digits
    }

    fun punctuations() {
        characterSet += CharacterSet.Punctuations
    }

    fun custom(custom: (MutableSet<CharacterSet>) -> Unit) {
        custom(characterSet)
    }

    fun alphanumeric() {
        lowercase()
        uppercase()
        digits()
        punctuations()
    }

    fun generate(): GeneratedPassword {
        val generated = (1..length).map {
            val charSet = characterSet.random(random)
            charSet to charSet.getChars().random(random)
        }

        val pwd = generated.map { it.second }.joinToString("")

        return GeneratedPassword(pwd, generated.groupBy { it.first }.keys)
    }
}

@PasswordGeneratorDsl
fun generatePassword(init: PasswordGenerator.() -> Unit): GeneratedPassword {
    val generator = PasswordGenerator()
    generator.init()
    return generator.generate()
}