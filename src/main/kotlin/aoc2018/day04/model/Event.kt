package aoc2018.day04.model

import java.time.LocalDateTime

data class Event(val timestamp: LocalDateTime, val event: String, val eventType: EventType)
