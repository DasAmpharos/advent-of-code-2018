package aoc2018.day04

import aoc2018.Challenge
import aoc2018.day04.model.*
import aoc2018.util.readFileAsStream
import java.io.File
import java.lang.IllegalStateException
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap
import kotlin.streams.toList

class Challenge01 : Challenge() {
    override fun solve(): String {
        var currentGuard = ""
        val guardWhoSleepsMost = readFileAsStream(getInputFile())
            .map { parseEvent(it) }
            .sorted { e1, e2 ->
                e1.timestamp.compareTo(e2.timestamp)
            }
            // for some reason kotlin doesn't like Collectors.groupingBy(...)
            // but this works...
            .toList()
            .groupBy {
                if (it.eventType == EventType.BEGINS_SHIFT) {
                    currentGuard = getGuardId(it.event)
                }
                currentGuard
             }
            .entries
            .map {
                var totalMinutesAsleep = 0L
                var fellAsleepAt = Optional.empty<LocalDateTime>()
                val timesOfSleep = ArrayList<TimeRange>()
                for (event in it.value) {
                    if (event.eventType == EventType.FALLS_ASLEEP) {
                        fellAsleepAt = Optional.of(event.timestamp)
                    } else if (event.eventType == EventType.WAKES_UP) {
                        if (!fellAsleepAt.isPresent) throw IllegalStateException("guard must fall asleep before waking up")
                        val timeRange = TimeRange(fellAsleepAt.get(), event.timestamp)
                        totalMinutesAsleep += timeRange.getDuration().toMinutes()
                        timesOfSleep.add(timeRange)
                        fellAsleepAt = Optional.empty()
                    }
                }
                Guard(it.key, timesOfSleep, totalMinutesAsleep)
            }
            .sortedBy { it.totalMinutesAsleep }
            .last()

        val minuteHeatMap = HashMap<Int, Int>()
        for (timeOfSleep in guardWhoSleepsMost.timesOfSleep) {
            for (minute in IntRange(timeOfSleep.start.minute, timeOfSleep.stop.minute - 1)) {
                val count = minuteHeatMap.getOrPut(minute) {0}
                minuteHeatMap[minute] = count + 1
            }
        }
        val minuteOfMostSleep = minuteHeatMap.entries
            .sortedBy { it.value }
            .last().key
        return (Integer.valueOf(guardWhoSleepsMost.id) * minuteOfMostSleep)
            .toString()
    }

    override fun getInputFile(): File {
        return File(javaClass
            .getResource("input.txt")
            .toURI())
    }
}

fun main(args: Array<String>) {
    val challenge = Challenge01()
    println(challenge.solve())
}
