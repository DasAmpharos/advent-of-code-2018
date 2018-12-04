package aoc2018.day03

import aoc2018.day03.model.Claim
import aoc2018.day03.model.Rectangle
import java.util.*

fun parseClaim(line: String): Claim {
    val split = line.split("@")
    return Claim(split[0].trim(),
        parseRectangle(split[1]))
}

fun parseRectangle(s: String): Rectangle {
    val split = s.split(":")
    val position = split[0].split(",")
    val size = split[1].split("x")
    return Rectangle(Integer.parseInt(position[0].trim()),
        Integer.parseInt(position[1].trim()),
        Integer.parseInt(size[0].trim()),
        Integer.parseInt(size[1].trim()))
}

fun buildFabric(claims: List<Claim>): Array<Array<String>> {
    val fabric = Array(1000) { Array(1000) { "" } }
    for (claim in claims) {
        for (col in 0 until claim.area.w) {
            for (row in 0 until claim.area.h) {
                val x = claim.area.x + col
                val y = claim.area.y + row
                fabric[y][x] = Optional.of(fabric[y][x])
                    .filter { it != "" }
                    .map { "$it, ${claim.claimNumber}" }
                    .orElse(claim.claimNumber)
            }
        }
    }
    return fabric
}

fun isAlreadyClaimed(claim: Claim, fabric: Array<Array<String>>, x: Int, y: Int): Boolean {
    return fabric[y][x] != "" && fabric[y][x] != claim.claimNumber
}