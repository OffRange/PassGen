package de.davis.passgen.markers

/**
 * This annotation is used to mark a class or an annotation class as part of a Domain-Specific Language (DSL)
 * for generating strings.
 *
 * Elements annotated with [GeneratorDsl] are considered part of the generator DSL and are subject to specific
 * language rules or transformations provided by the DSL implementation.
 *
 */
@DslMarker
annotation class GeneratorDsl

/**
 * This annotation is used to mark classes, functions, or properties that are part of a Domain-Specific
 * Language (DSL) for configuring string generators.
 *
 * Elements annotated with [ConfigDsl] are considered part of the configuration DSL and can be used
 * within the DSL blocks or lambdas to set up the configuration for string generators.
 */
@DslMarker
annotation class ConfigDsl