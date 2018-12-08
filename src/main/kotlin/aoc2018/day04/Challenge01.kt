package aoc2018.day04

import aoc2018.Challenge
import aoc2018.day04.model.Event
import aoc2018.day04.model.EventType
import aoc2018.util.readFileAsStream
import java.io.File
import kotlin.streams.toList

class Challenge01 : Challenge() {
    override fun solve(): String {
        var currentGuard = ""
        val guardEventMap = readFileAsStream(getInputFile())
            .map { parseEvent(it) }
            .sorted { e1, e2 ->
                e1.timestamp.compareTo(e2.timestamp)
            }
            .toList()
            .groupBy {
                if (it.eventType == EventType.BEGINS_SHIFT) {
                    currentGuard = getGuardId(it.event)
                }
                currentGuard
            }
        println(guardEventMap)
        return ""
    }

    override fun getInputFile(): File {
        return File(javaClass
            .getResource("input.txt")
            .toURI())
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge01()
    challenge.solve()
}
