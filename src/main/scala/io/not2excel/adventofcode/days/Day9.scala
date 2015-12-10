package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day9 {

    val data = Main.resource("day9.data").getLines().toList
    val RouteRegex = "(.+) to (.+) = ([0-9]+)".r

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val mappedRoutes = data.map { case RouteRegex(src, dest, dist) => Route(src, dest, dist.toInt)
                                 } groupBy { _.src }
        mappedRoutes.foreach(p => {
            println(s"${p._1} | ${p._2}")
        })
    }

    def partTwo() = {

    }

    case class Route(src: String, dest: String, dist: Int)

}
