package io.not2excel.adventofcode.traits

import io.not2excel.adventofcode.Main

trait Template {

    val data = Main.resource("dayX.data").getLines().toList

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {

    }

    def partTwo() = {

    }

}
