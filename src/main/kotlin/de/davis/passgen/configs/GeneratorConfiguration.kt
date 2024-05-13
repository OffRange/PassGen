package de.davis.passgen.configs

import de.davis.passgen.markers.ConfigDsl
import de.davis.passgen.pool.GenerationPool

/**
 * Represents a configuration for generating elements of type [GenerationType].
 * This class provides settings for the generation process such as length and character pools.
 * @param GenerationType The type of elements to generate.
 */
@ConfigDsl
open class GeneratorConfiguration<GenerationType> {

    private val _pools = mutableSetOf<GenerationPool<GenerationType>>()
    val pools get() = _pools.toList()

    /**
     * The length of the generated elements. Default value is 8.
     * @throws IllegalArgumentException if the specified length is not positive.
     */
    @ConfigDsl
    var length = 8
        set(value) {
            if (value <= 0)
                throw IllegalArgumentException("The length must be a positive, non-zero number.")

            field = value
        }

    /**
     * Returns a range from 1 to the current length.
     */
    val range get() = (1..length)

    /**
     * Adds the specified generation pool to the configuration.
     *
     * @receiver The character pool to add.
     */
    @ConfigDsl
    operator fun GenerationPool<GenerationType>.unaryPlus() = addPool(this)

    /**
     * Adds the specified generation pool to the configuration.
     * @param generationPool The generation pool to add.
     * @throws IllegalArgumentException if a generation pool with the same content already exists.
     */
    @ConfigDsl
    protected fun addPool(generationPool: GenerationPool<GenerationType>) {
        _pools.firstOrNull { it == generationPool || it in generationPool }?.let {
            throw IllegalArgumentException("There is already a generation pool like $generationPool registered")
        }

        _pools.add(generationPool)
    }

    /**
     * Adds a custom generation pool to the configuration.
     *
     * @param pool The custom character pool to add.
     */
    @ConfigDsl
    fun custom(pool: GenerationPool<GenerationType>) = +pool

    /**
     * Sets the length of the generated elements.
     * @param length The length to set.
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
fun <GenerationType, Config : GeneratorConfiguration<GenerationType>> Config.verified(): Config {
    if (pools.isEmpty())
        throw IllegalArgumentException("To generate a password, you must specify at least one generation pool!")

    return this
}