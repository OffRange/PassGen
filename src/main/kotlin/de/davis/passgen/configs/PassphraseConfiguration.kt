package de.davis.passgen.configs

import de.davis.passgen.markers.ConfigDsl
import de.davis.passgen.pool.StringPool
import de.davis.passgen.pool.toStringPool
import java.io.File

/**
 * Represents a configuration for generating passphrases.
 * Inherits from [GeneratorConfiguration] with elements of type [String].
 */
@ConfigDsl
class PassphraseConfiguration : GeneratorConfiguration<String>() {

    /**
     * The separator used between elements of the passphrase. Default value is "-".
     */
    @ConfigDsl
    var separator = "-"

    /**
     * Loads lines from the specified file and adds them as a [StringPool] to the configuration.
     * @param file The file containing lines to load as a generation pool.
     */
    @ConfigDsl
    fun loadFile(file: File) = +file.readLines().toStringPool()
}