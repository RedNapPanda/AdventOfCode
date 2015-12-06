package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main
import io.not2excel.adventofcode.template.Template

object Day1 extends Template {

    lazy val input = Main.sourceFile("day1.data").getLines().next()

    override def partOne() = {
        println(s"Floor: ${input.count(_ == '(') - input.count(_ == ')')}")
        println()
    }

    override def partTwo() = {
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
