package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

import scala.collection.immutable.HashMap

object Day9 {

    val data = Main.resource("day9.data").getLines().toList
    val RouteRegex = "(.+) to (.+) = ([0-9]+)".r
    var mappedRoutes = HashMap[String, Map[String, Int]]()
    data.foreach {
                     case RouteRegex(loc1, loc2, dist) =>
                         addRoute(loc1, loc2, dist)
                         addRoute(loc2, loc1, dist)
                 }

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        println(s"Min: ${calcRouteDist.min}")
    }

    def partTwo() = {
        println(s"Max: ${calcRouteDist.max}")
    }

    def addRoute(loc1: String, loc2: String, dist: String) = {
        mappedRoutes.get(loc1) match {
            case Some(m) => mappedRoutes += (loc1 -> (m + (loc2 -> dist.toInt)))
            case _ => mappedRoutes += (loc1 -> Map(loc2 -> dist.toInt))
        }
    }

    def calcRouteDist = {
        mappedRoutes.keySet.toSeq.permutations.map(s => {
            var i = 0
            s.reduce((l1, l2) => { i += mappedRoutes.get(l1).get.get(l2).get; l2 })
            i
        })
    }
}
