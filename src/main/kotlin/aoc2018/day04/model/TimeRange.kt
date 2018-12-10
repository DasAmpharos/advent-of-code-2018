package aoc2018.day04.model

import java.time.Duration
import java.time.LocalDateTime

data class TimeRange(val start: LocalDateTime, val stop: LocalDateTime)

fun TimeRange.getDuration(): Duration {
    return Duration.between(start, stop)
}
