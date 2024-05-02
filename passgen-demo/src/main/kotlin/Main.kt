package de.davis.passgen.demo

import de.davis.passgen.configs.*
import de.davis.passgen.generators.Passphrase
import de.davis.passgen.generators.Password
import de.davis.passgen.generators.generate
import de.davis.passgen.pool.toStringPool
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI


fun main(args: Array<String>) {
    println(args.joinToString())
    if (args.isEmpty() || args[0].equals("-w", ignoreCase = true)) {
        // Show wizzard for generating a password
        startWizard()
    } else if (args[0].equals("-b", ignoreCase = true)) {
        // Start a benchmark
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
            generate(Password) {
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

    println("AVG: ${times.average() / 1e6}ms")
}

fun startWizard() {
    println("What do you want to generate?")
    println("y: Password")
    println("n: Passphrase")
    if (readBool("What should it be?")) startPasswordWizard() else startPassphraseWizard()
}

fun PassphraseConfiguration.loadFromWeb(urlStr: String) {
    val url = URI(urlStr).toURL()
    val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))

    val lines = mutableListOf<String>()
    bufferedReader.use {
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            lines.add(line!!)
        }
    }

    +lines.toStringPool()
}

fun startPasswordWizard() {
    val lower = readBool("Should the password contain lowercase letters?")
    val upper = readBool("Should the password contain uppercase letters?")
    val digits = readBool("Should the password contain digits?")
    val punctuations = readBool("Should the password contain punctuations?")

    if (!(lower || upper || digits || punctuations)) {
        error("Password needs some characters!")
    }

    val length = readLength(8)

    val (duration, password) = mesTime {
        generate(Password) {
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
    println("Generated password : $password")
    println("Length             : ${password.length}")
    println("Created in         : ${duration / 1e6} ms")
}

fun startPassphraseWizard() {
    val length = readLength(8)

    val (duration, passphrase) = mesTime {
        generate(Passphrase) {
            length(length)
            println("Fetching english words from https://raw.githubusercontent.com/dolph/dictionary/master/popular.txt")
            loadFromWeb("https://raw.githubusercontent.com/dolph/dictionary/master/popular.txt")
        }
    }

    println("----------------------------------------")
    println("Generated passphrase : $passphrase")
    println("Words                : ${passphrase.split('-').count()}")
    println("Total length         : ${passphrase.length}")
    println("Created in           : ${duration / 1e6} ms")
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
    print("How long should the string be? [$defaultVal]: ")
    val result = readln()

    return result.toIntOrNull() ?: defaultVal
}