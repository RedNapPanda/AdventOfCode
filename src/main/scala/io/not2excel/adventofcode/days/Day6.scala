package io.not2excel.adventofcode.days

import java.util.regex.Pattern

import io.not2excel.adventofcode.Main
import io.not2excel.adventofcode.traits.Template

object Day6 extends Template {

    lazy val input = Main.sourceFile("day6.data").getLines().toList
    lazy val regex = Pattern.compile("(.*)\\s(\\d*),(\\d*)\\s.*\\s(\\d*),(\\d*)")

    override def partOne(): Unit = {
        val grid = Array.ofDim[Boolean](1000, 1000)
        input.foreach(s => {
            val data = parseData(s)
            val start = data._2
            val end = data._3
            val xs = start._1 to end._1
            val ys = start._2 to end._2
            data._1 match {
                case "turn off" => xs.foreach(x => ys.foreach(y => grid(x)(y) = false))
                case "turn on" => xs.foreach(x => ys.foreach(y => grid(x)(y) = true))
                case "toggle" => xs.foreach(x => ys.foreach(y => grid(x)(y) = !grid(x)(y)))
            }
        })
        val count = (0 /: (0 until 1000)) ((i, x) => (0 /: (0 until 1000)) ((j, y) => if(grid(x)(y)) j + 1; else j) + i)
        println(s"Number of lights on: $count")
    }

    def parseData(s: String): (String, (Int, Int), (Int, Int)) = {
        val m = regex.matcher(s)
        m.find()
        Tuple3(m.group(1),
            (m.group(2).toInt, m.group(3).toInt),
            (m.group(4).toInt, m.group(5).toInt))
    }

    override def partTwo(): Unit = {
        val grid2 = Array.ofDim[Int](1000, 1000)
        input.foreach(s => {
            val data = parseData(s)
            val start = data._2
            val end = data._3
            val xs = start._1 to end._1
            val ys = start._2 to end._2
            data._1 match {
                case "turn off" => xs.foreach(x => ys.foreach(y => {
                    grid2(x)(y) -= 1
                    if(grid2(x)(y) < 0) grid2(x)(y) = 0
                }))
                case "turn on" => xs.foreach(x => ys.foreach(y => grid2(x)(y) += 1))
                case "toggle" => xs.foreach(x => ys.foreach(y => grid2(x)(y) += 2))
            }
        })
        val brightness = (0 /: (0 until 1000)) ((i, x) => (0 /: (0 until 1000)) ((j, y) => grid2(x)(y) + j) + i)
        println(s"Total brightness of lights: $brightness")
    }
}
