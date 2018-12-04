package aoc2018.day01

import aoc2018.Challenge
import aoc2018.util.readFileAsStream
import java.io.File

open class Challenge01(private val startingFrequency: Int) : Challenge() {

    override fun solve(): String {
        val sum = readFileAsStream(getInputFile())
                .map { Integer.parseInt(it) }
                .reduce(startingFrequency) { a: Int, b: Int -> a + b }
        return Integer.toString(startingFrequency + sum)
    }

    override fun getInputFile(): File {
        return File(javaClass
                .getResource("input.txt")
                .toURI())
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge01(0)
    println(challenge.solve())
}
