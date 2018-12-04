package aoc2018.util

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

fun readFileAsString(inputFile: File): String {
    val path = Paths.get(inputFile.toURI())
    val bytes = Files.readAllBytes(path)
    return String(bytes)
}

fun readFileAsLines(inputFile: File): List<String> {
    val path = Paths.get(inputFile.toURI())
    return Files.readAllLines(path)
}

fun readFileAsStream(inputFile: File): Stream<String> {
    val path = Paths.get(inputFile.toURI())
    return Files.lines(path)
}