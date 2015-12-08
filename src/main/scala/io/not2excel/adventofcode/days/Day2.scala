package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main
import io.not2excel.adventofcode.traits.Template

object Day2 {

    val data = Main.resource("day2.data").getLines().toList.map(s => s.split("x").map(_.toInt))

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val data = this.data.map(a => List(a(0) * a(1), a(1) * a(2), a(2) * a(0)))
        val amount = (0 /: data) ((i, s) => i + s.min + s.map(_ * 2).sum)
        println(amount)
    }

    def partTwo() = {
        val data = this.data.map(_.sorted.toList)
        val amount = (0 /: data) ((i, d) => i + d.product + (d.head * 2 + d(1) * 2))
        println(amount)
    }
}
