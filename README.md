# PassGen

PassGen is a Kotlin DSL-driven password generator that allows you to create secure and customizable passwords with ease.

## Installation [![](https://jitpack.io/v/OffRange/PassGen.svg)](https://jitpack.io/#OffRange/PassGen)

You can include PassGen in your Kotlin project via [JitPack](https://jitpack.io/#OffRange/PassGen).

### Gradle Kotlin DSL

For Gradle Kotlin DSL (`build.gradle.kts`), add the JitPack repository and dependency as follows:

```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.offrange:passgen:v0.0.2-beta")
}
```

### Maven

For Maven, add the JitPack repository and dependency to your `pom.xml` file:

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then, include the PassGen dependency:

```xml

<dependency>
    <groupId>com.github.offrange</groupId>
    <artifactId>passgen</artifactId>
    <version>v0.0.2-beta</version>
</dependency>
```

## Usage

### Basic Password Generation

Generate a password using lowercase letters, uppercase letters, digits, and punctuations that is 12 characters long:

```kotlin
fun main() {
    val password = generate(Password) {
        lowercase()
        uppercase()
        digits()
        punctuations()
        length(12)
    }

    println("Generated Password: $password")
}
```

### Alphanumeric Password

Generate an alphanumeric password (including lowercase letters, uppercase letters, digits, and punctuations)
using `alphanumeric()`. This function internally utilizes the functions defined in the example above to
include the necessary generation pools:

```kotlin
fun main() {
    val password = generate(Password) {
        alphanumeric()
        length(12)
    }

    println("Generated Alphanumeric Password: $password")
}
```

### Custom Generation Pools

Create a password using a custom generation pool:

```kotlin
fun main() {
    val customPool = "ABC123".toCharPool() // Same as GenerationPool.fromString("ABC1234")

    val password = generate(Password) {
        custom(customPool)
        length(8)
    }

    println("Generated Password with Custom Generation Pool: $password")
}
```

### Multiple Custom Generation Pools

Combine multiple custom generation pools to generate a password:

```kotlin
fun main() {
    val customPool1 = "ABC".toCharPool()
    val customPool2 = "123".toCharPool()

    val password = generate(Password) {
        +customPool1 // Same as custom(customPool1)
        +customPool2 // Same as custom(customPool2)
        length(10)
    }

    println("Generated Password with Multiple Custom Generation Pools: $password")
}
```

### Generate Passphrases

In addition to generating passwords, you can use the library to generate passphrases.

```kotlin
fun main() {
    val passphrase = generate(Passphrase) {
        separator = '-' // Default separator between words
        custom(GenerationPool.fromStrings("word1", "word2", "word3")) // Add custom words to the passphrase pool
        loadFile(File("path/to/wordlist")) // Load words from a file as entries in the passphrase pool
    }

    println("Generated Passphrase: $passphrase")
}
```

## Contributing

Contributions to PassGen are welcome! Feel free to open issues and pull requests on
the [GitHub repository](https://github.com/OffRange/PassGen).

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.
