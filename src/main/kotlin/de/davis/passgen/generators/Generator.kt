package de.davis.passgen.generators

import de.davis.passgen.configs.GeneratorConfiguration
import de.davis.passgen.markers.GeneratorDsl
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

/**
 * An abstract class that represents a generator for creating strings based on a specific configuration.
 *
 * The `Generator` class is designed to be highly configurable and extensible, allowing for the creation
 * of various types of string generators, such as password generators, random string generators, or
 * other custom generators, by providing the appropriate configuration.
 *
 * @param Config The type of the configuration class that extends [GeneratorConfiguration]. This class
 *               defines the configuration options for the generator.
 * @property createConfig A lambda function that provides the configuration object for the generator.
 *                          This allows for lazy initialization and dynamic configuration of the generator.
 * @property random The [Random] instance used by the generator for generating random values. By default,
 *                  a secure random instance is used, but this can be overridden if needed.
 */
@GeneratorDsl
abstract class Generator<Config : GeneratorConfiguration>(
    val createConfig: () -> Config,
    val random: Random = SecureRandom().asKotlinRandom()
) {

    /**
     * Generates a string based on the provided configuration and returns it.
     *
     * @param config The configuration object to use for generating the string.
     * @return The generated string based on the provided configuration.
     */
    abstract fun generate(config: Config): String
}

/**
 * A type alias representing a function that configures an object of type C.
 */
typealias ConfigDeclaration<C> = C.() -> Unit

/**
 * Generates a string using the provided [generator] and [configure] function.
 *
 * @param generator The [Generator] instance to be used for generating the string.
 * @param configure The configuration function that sets up the generator's configuration.
 * @return The generated string.
 */
@GeneratorDsl
inline fun <Config : GeneratorConfiguration> generate(
    generator: Generator<Config>,
    configure: ConfigDeclaration<Config>
): String = generator.run {
    generate(createConfig().apply(configure))
}