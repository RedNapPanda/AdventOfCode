package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day1 {

    val input = Main.sourceFile("day1.data").getLines().next()

    def main(args: Array[String]): Unit = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        println(s"Floor: ${input.count(_ == '(') - input.count(_ == ')')}")
        println()
    }

    def partTwo() = {
        var position = 0
        (0 /: input) ((i, c) => {
            position = position + 1
            c match {
                case '(' => i + 1
                case ')' =>
                    if(i == 0) println(s"Basement: ${i-1}, Position: $position")
                    i - 1
            }
        })
    }
}
