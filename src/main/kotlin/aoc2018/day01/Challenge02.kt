package aoc2018.day01

import aoc2018.Challenge
import aoc2018.util.readFileAsLines
import java.io.File
import java.nio.file.Files
import java.util.*

class Challenge02(private val startingFrequency: Int) : Challenge() {
    override fun solve(): String {
        val frequencies = HashSet<Int>()
        val lines = readFileAsLines(getInputFile())
        var firstDuplicate = Optional.empty<Int>()

        var iterations = 0
        var currentFrequency = startingFrequency
        while (!firstDuplicate.isPresent) {
            for (line in lines) {
                currentFrequency += Integer.parseInt(line)
                if (frequencies.contains(currentFrequency)) {
                    firstDuplicate = Optional.of(currentFrequency)
                    break
                } else {
                    frequencies.add(currentFrequency)
                }
            }
            iterations++
        }
        println("first duplicate frequency found!")
        println("total number of iterations: $iterations")
        return Integer.toString(firstDuplicate.get())
    }

    override fun getInputFile(): File {
        return File(javaClass
                .getResource("input.txt")
                .toURI())
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge02(0)
    println(challenge.solve())
}