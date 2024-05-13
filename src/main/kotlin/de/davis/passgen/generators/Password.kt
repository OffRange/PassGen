package de.davis.passgen.generators

import de.davis.passgen.configs.PasswordGeneratorConfig

/**
 * A class for generating passwords based on the provided configuration.
 */
class Password {

    /**
     * Companion object for generating passwords.
     */
    companion object : Generator<Char, PasswordGeneratorConfig>(::PasswordGeneratorConfig) {

        /**
         * Generates a password string based on the current configuration.
         *
         * @param config The configuration object to use for generating the password.
         * @return The generated password string.
         * @throws IllegalArgumentException If no generation pools are specified in the configuration.
         */
        override fun generate(config: PasswordGeneratorConfig): String = config.run {
            if (poolsEvenlyDistributed) {
                return range.joinToString("") {
                    val pool = pools.random(random)
                    pool.random(random).toString()
                }
            }

            val pool = pools.reduce { acc, characterSet -> acc + characterSet }
            return range.joinToString("") { pool.random(random).toString() }
        }
    }
}