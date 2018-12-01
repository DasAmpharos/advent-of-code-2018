package aoc2018.day01

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

open class Challenge01(val startingFrequency: Int) {

    open fun solve(): String {
        val sum = Files.lines(getFilePath())
                .map { Integer.parseInt(it) }
                .reduce(startingFrequency) { a: Int, b: Int -> a + b }
        return Integer.toString(startingFrequency + sum)
    }

    protected fun getFilePath(): Path {
        return Paths.get(javaClass
                .getResource("input.txt")
                .toURI())
    }

}

fun main(args: Array<String>) {
    val challenge = Challenge01(0)
    println(challenge.solve())
}
