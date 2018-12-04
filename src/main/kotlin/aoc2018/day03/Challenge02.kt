package aoc2018.day03

import aoc2018.Challenge
import aoc2018.day03.model.Claim
import aoc2018.util.readFileAsStream
import java.io.File
import kotlin.streams.toList

class Challenge02 : Challenge() {

    override fun solve(): String {
        val claims = readFileAsStream(getInputFile())
            .map { parseClaim(it) }
            .toList()
        val fabric = buildFabric(claims)
        return claims.stream()
            .filter { !isInConflict(it, fabric) }
            .findFirst()
            .orElseThrow { IllegalStateException("unable to find non-conflicting claim") }
            .claimNumber
    }

    override fun getInputFile(): File {
        return File(javaClass
            .getResource("input.txt")
            .toURI())
    }

    private fun isInConflict(claim: Claim, fabric: Array<Array<String>>): Boolean {
        for (col in 0 until claim.area.w) {
            for (row in 0 until claim.area.h) {
                val x = claim.area.x + col
                val y = claim.area.y + row
                if (isAlreadyClaimed(claim, fabric, x, y)) {
                    return true
                }
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge02()
    println(challenge.solve())
}