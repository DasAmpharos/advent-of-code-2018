package aoc2018.day02

import aoc2018.Challenge
import aoc2018.util.readFileAsLines
import java.io.File

class Challenge02 : Challenge() {

    override fun solve(): String {
        val lines = readFileAsLines(getInputFile())

        var i = 0
        while (i < lines.size) {
            var j = i + 1
            val s1 = lines[i]
            while (j < lines.size) {
                val s2 = lines[j]
                val commonString = getCommonString(s1, s2)
                if (commonString.length == s1.length - 1) {
                    return commonString
                }
                j++
            }
            i++
        }
        throw IllegalStateException("unable to find the correct ID")
    }

    private fun getCommonString(s1: String, s2: String): String {
        if (s1.length != s2.length) throw IllegalArgumentException("cannot compare two strings of different lengths")

        var i = 0
        var result = ""
        while (i < s1.length) {
            val char1 = s1[i]
            val char2 = s2[i]
            if (char1 == char2) result += char1
            i++
        }

        return result
    }

    override fun getInputFile(): File {
        return File(javaClass
                .getResource("input.txt")
                .toURI())
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge02()
    println(challenge.solve())
}