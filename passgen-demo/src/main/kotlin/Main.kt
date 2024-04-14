package de.davis.passgen.demo

import de.davis.passgen.generatePassword

fun main(args: Array<String>) {
    println(args.joinToString())
    if (args.isEmpty() || args[0].equals("-w", ignoreCase = true))
        startWizard()
    else if (args[0].equals("-b", ignoreCase = true)) {
        doBenchmark()
    } else
        error("-w or -w expected")
}

fun doBenchmark(
    iterations: Int = 50_000,
    length: Int = 8,
    lower: Boolean = true,
    upper: Boolean = true,
    digits: Boolean = true,
    punctuations: Boolean = true
) {
    val times = mutableListOf<Long>()
    repeat(iterations) {
        val (duration, _) = mesTime {
            generatePassword {
                if (lower)
                    lowercase()

                if (upper)
                    uppercase()

                if (digits)
                    digits()

                if (punctuations)
                    punctuations()

                length(length)
            }
        }

        times.add(duration)
    }

    println("AVG: ${times.average() / 1e6}")
}

fun startWizard() {
    val lower = readBool("Should the password contain lowercase letters?")
    val upper = readBool("Should the password contain uppercase letters?")
    val digits = readBool("Should the password contain digits?")
    val punctuations = readBool("Should the password contain punctuations?")

    if (!(lower || upper || digits || punctuations)) {
        error("Password needs some characters!")
    }

    val length = readLength(8)

    val (duration, password) = mesTime {
        generatePassword {
            if (lower)
                lowercase()

            if (upper)
                uppercase()

            if (digits)
                digits()

            if (punctuations)
                punctuations()

            length(length)
        }
    }

    println("----------------------------------------")
    println("Generated password : ${password.value}")
    println("Length             : ${password.length}")
    println("Used characters    : ${password.usedCharacterSets.joinToString()}")
    println("Created in         : ${duration / 1e6} ms")
}

fun <R> mesTime(l: () -> R): Pair<Long, R> {
    val start = System.nanoTime()
    val r = l()
    return System.nanoTime() - start to r
}

fun readBool(msg: String): Boolean {
    print("$msg y/N: ")
    val result = readln()

    return result.equals("y", ignoreCase = true)
}

fun readLength(defaultVal: Int = 8): Int {
    print("How long should the password be? [$defaultVal]: ")
    val result = readln()

    return result.toIntOrNull() ?: defaultVal
}