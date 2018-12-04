package aoc2018

import java.io.File

abstract class Challenge {

    abstract fun solve(): String

    abstract fun getInputFile(): File
}