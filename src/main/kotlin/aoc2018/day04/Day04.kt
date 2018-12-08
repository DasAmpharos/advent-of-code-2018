package aoc2018.day04

import aoc2018.day04.model.Event
import aoc2018.day04.model.EventType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"
private val EVENT_REGEX = "\\[(.*)\\] (.*)".toRegex()

private val BEGINS_SHIFT_REGEX = "Guard #([0-9]*) begins shift".toRegex()
private val FALLS_ASLEEP_REGEX = "falls asleep".toRegex()
private val WAKES_UP_REGEX = "wakes up".toRegex()

fun parseEvent(s: String): Event {
    val groups = EVENT_REGEX.matchEntire(s)!!.groupValues
    return Event(parseTimestamp(groups[1]), groups[2], getEventType(groups[2]))
}

fun parseTimestamp(s: String): LocalDateTime {
    return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
}

fun getEventType(s: String): EventType {
    return when {
        BEGINS_SHIFT_REGEX matches s -> EventType.BEGINS_SHIFT
        FALLS_ASLEEP_REGEX matches s -> EventType.FALLS_ASLEEP
        WAKES_UP_REGEX matches s -> EventType.WAKES_UP
        else -> throw IllegalArgumentException("unable to find EventType")
    }
}

fun getGuardId(s: String): String {
    return BEGINS_SHIFT_REGEX.matchEntire(s)!!.groupValues[1]
}
