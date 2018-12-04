package aoc2018.day03

import aoc2018.Challenge
import aoc2018.day03.model.Claim
import aoc2018.day03.model.Rectangle
import aoc2018.util.readFileAsStream
import java.io.File
import kotlin.math.min
import kotlin.streams.toList

class Challenge01 : Challenge() {
    override fun solve(): String {
        val claims = readFileAsStream(getInputFile())
                .map { parseClaim(it) }
                .toList()
        var totalConflictingArea = 0

        var i = 0
        while (i < claims.size) {
            var j = i + 1
            while (j < claims.size) {
                totalConflictingArea += getConflictingArea(claims[i], claims[j])
                j++
            }
            i++
        }
        return Integer.toString(totalConflictingArea)
    }

    override fun getInputFile(): File {
        return File(javaClass
                .getResource("input.txt")
                .toURI())
    }

    private fun getConflictingArea(claim1: Claim, claim2: Claim): Int {
        return if (overlaps(claim1.area, claim2.area)) {
            val rectangles = Pair(claim1.area, claim2.area)
            getHorizontalOverlap(rectangles) * getVerticalOverlap(rectangles)
        } else {
            0
        }
    }

    private fun overlaps(rect1: Rectangle, rect2: Rectangle): Boolean {
        return (rect1.x <= rect2.x && rect2.x <= (rect1.x + rect1.w))
                && (rect2.y <= rect1.y && rect1.y <= (rect2.y + rect2.h))
    }

    private fun getVerticalOverlap(r1: Rectangle, r2: Rectangle): Int {
        val min = minY(r1, r2)
        return min(sortedRectangles.first.y + sortedRectangles.first.h,
                sortedRectangles.second.y + sortedRectangles.second.h) -
                sortedRectangles.second.y
    }

    private fun getHorizontalOverlap(rectangles: Pair<Rectangle, Rectangle>): Int {
        val sortedRectangles = minX(rectangles)
        return min(sortedRectangles.first.x + sortedRectangles.first.w,
                sortedRectangles.second.x + sortedRectangles.second.x) -
                sortedRectangles.second.x
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge01()
    println(challenge.solve())
}