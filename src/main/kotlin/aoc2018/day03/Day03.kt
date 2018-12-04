package aoc2018.day03

import aoc2018.day03.model.Claim
import aoc2018.day03.model.Rectangle

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

fun minX(r1: Rectangle, r2: Rectangle): Rectangle {
    return if (r1.x <= r2.x) {
        r1
    } else {
        r2
    }
}

fun minY(r1: Rectangle, r2: Rectangle): Rectangle {
    return if (r1.y <= r2.y) {
        r1
    } else {
        r2
    }
}