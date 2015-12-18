package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main
import org.json4s._
import org.json4s.native.JsonMethods._

object Day12 {

    implicit val formats = DefaultFormats

    val data = Main.resource("day12.data").getLines().next()
    val json = parse(data)

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        println(s"Sum: ${json.children.map(filterChildren(_, j => false)).sum}")
    }

    def partTwo() = {
        println(s"Red: ${json.children.map(filterChildren(_, j => j.children contains JString("red"))).sum}")
    }

    def filterChildren(jValue: JValue, filter :(JValue) => Boolean): Int = {
        jValue match {
            case j: JInt => j.extract[Int]
            case j: JObject =>
                if(filter.apply(j)) 0
                else j.children.map(filterChildren(_, filter)).sum
            case _ => jValue.children.map(filterChildren(_, filter)).sum

        }
    }
}
