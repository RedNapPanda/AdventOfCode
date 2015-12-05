package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day2 {

    val input = Main.sourceFile("day2.data").getLines().toList.map(s => s.split("x").map(_.toInt))

    def main(args: Array[String]): Unit = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val input = this.input.map(a => List(a(0) * a(1), a(1) * a(2), a(2) * a(0)))
        val amount = (0 /: input) ((i, s) => i + s.min + s.map(_ * 2).sum)
        println(amount)
    }

    def partTwo() = {
        val input = this.input.map(_.sorted.toList)
        val amount = (0 /: input) ((i, d) => i + d.product + (d.head * 2 + d(1) * 2))
        println(amount)
    }
}