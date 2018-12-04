package aoc2018.day02

import aoc2018.Challenge
import aoc2018.util.readFileAsLines
import java.io.File

class Challenge01 : Challenge() {

    override fun solve(): String {
        var twoCount = 0
        var threeCount = 0
        for (line in readFileAsLines(getInputFile())) {
            val processingResult = processLine(line)
            if (processingResult.first) twoCount += 1
            if (processingResult.second) threeCount += 1
        }
        return (twoCount * threeCount).toString()
    }

    override fun getInputFile(): File {
        return File(javaClass
                .getResource("input.txt")
                .toURI())
    }

    private fun processLine(line: String): Pair<Boolean, Boolean> {
        val characterMap = HashMap<Char, Int>()
        line.chars().mapToObj(Int::toChar)
                .forEach {
                    val count = characterMap.getOrPut(it) { 0 }
                    characterMap[it] = count + 1
                }
        return Pair(characterMap.values.any { it == 2 },
                characterMap.values.any { it == 3 })
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge01()
    println(challenge.solve())
}