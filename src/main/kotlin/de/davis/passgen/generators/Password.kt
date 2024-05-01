package de.davis.passgen.generators

import de.davis.passgen.configs.PasswordGeneratorConfig

/**
 * A class for generating passwords based on the provided configuration.
 */
class Password {

    companion object : Generator<PasswordGeneratorConfig>(::PasswordGeneratorConfig) {

        /**
         * Generates a password string based on the current configuration.
         *
         * @return The generated password string.
         * @throws IllegalArgumentException If no character sets are specified in the configuration.
         */
        override fun generate(config: PasswordGeneratorConfig): String {
            if (config.characterSets.isEmpty())
                throw IllegalArgumentException("To generate a password, you must specify at least one character set!")

            val range = (1..config.length)

            if (config.charSetsEvenlyDistributed) {
                val generated = range.map {
                    val charSet = config.characterSets.random(random)
                    charSet.characters.random(random)
                }

                return generated.joinToString("")
            }

            val characterSet = config.characterSets.reduce { acc, characterSet -> acc + characterSet }
            val generated = range.map { characterSet.characters.random(random) }
            return generated.joinToString("")
        }
    }
}