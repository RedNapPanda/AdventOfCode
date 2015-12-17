package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day1 {

    val data = Main.resource("day1.data").getLines().next()

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
        println(join("rip", "in", "breakers"))
    }

    def join(msgs: String*): String = {
        msgs.mkString(".")
    }

    def partOne() = {
        println(s"Floor: ${data.count(_ == '(') - data.count(_ == ')')}")
        println()
    }

    def partTwo() = {
        var position = 0
        (0 /: data) ((i, c) => {
            position = position + 1
            c match {
                case '(' => i + 1
                case ')' =>
                    if(i == 0) println(s"Basement: ${i - 1}, Position: $position")
                    i - 1
            }
        })
    }
}
