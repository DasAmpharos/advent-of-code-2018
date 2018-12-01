package aoc2018.day01

import java.nio.file.Files
import java.util.*

class Challenge02(startingFrequency: Int) : Challenge01(startingFrequency) {
    override fun solve(): String {
        val frequencies = HashSet<Int>()
        val lines = Files.readAllLines(getFilePath())
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
}

fun main(args: Array<String>) {
    val challenge = Challenge02(0)
    println(challenge.solve())
}