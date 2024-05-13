package de.davis.passgen.generators

import de.davis.passgen.configs.PassphraseConfiguration

/**
 * A class for generating passphrases based on the provided configuration.
 */
class Passphrase {

    /**
     * Companion object for generating passphrases.
     */
    companion object : Generator<String, PassphraseConfiguration>(::PassphraseConfiguration) {

        /**
         * Generates a passphrase string based on the current configuration.
         *
         * @param config The configuration object to use for generating the passphrase.
         * @return The generated passphrase string.
         */
        override fun generate(config: PassphraseConfiguration): String = config.run {
            range.joinToString(separator) {
                val pool = pools.random(random)
                pool.random(random)
            }
        }
    }
}