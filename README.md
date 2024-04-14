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
    implementation("com.github.offrange:passgen:tag")
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
    <version>tag</version>
</dependency>
```

Replace `your-username` with your GitHub username and `tag` with the desired release tag or commit hash.

## Usage

### Basic Password Generation

Generate a password using lowercase letters, uppercase letters, digits, and punctuations:

```kotlin
fun main() {
    val password = generatePassword {
        lowercase()
        uppercase()
        digits()
        punctuations()
        length(12)
    }

    println("Generated Password: ${password.value}")
}
```

### Alphanumeric Password

Generate an alphanumeric password (including lowercase letters, uppercase letters, digits, and punctuations)
using `alphanumeric()`. This function internally utilizes the functions defined in the example above to
include the necessary character sets:

```kotlin
fun main() {
    val password = generatePassword {
        alphanumeric()
        length(10)
    }

    println("Generated Alphanumeric Password: ${password.value}")
}
```

### Custom Character Sets

Create a password using a custom character set:

```kotlin
fun main() {
    val customCharacterSet = CharacterSet.Custom("ABC123")

    val password = generatePassword {
        custom { characterSets ->
            characterSets.add(customCharacterSet)
        }
        length(8)
    }

    println("Generated Password with Custom Character Set: ${password.value}")
}
```

### Multiple Custom Character Sets

Combine multiple custom character sets to generate a password:

```kotlin
fun main() {
    val customCharacterSet1 = CharacterSet.Custom("ABC")
    val customCharacterSet2 = CharacterSet.Custom("123")

    val password = generatePassword {
        custom { characterSets ->
            characterSets.add(customCharacterSet1)
            characterSets.add(customCharacterSet2)
        }
        length(10)
    }

    println("Generated Password with Multiple Custom Character Sets: ${password.value}")
}
```

## Contributing

Contributions to PassGen are welcome! Feel free to open issues and pull requests on
the [GitHub repository](https://github.com/OffRange/PassGen).

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.