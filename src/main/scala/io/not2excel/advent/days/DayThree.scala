package io.not2excel.advent.days

import io.not2excel.advent.Main

import scala.collection.mutable

object DayThree {

    val input = Main.sourceFile("d3.data").getLines().next()

    def main(args: Array[String]): Unit = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        var x, y = 0
        val coords: mutable.HashSet[(Int, Int)] = mutable.HashSet()
        coords.add((x, y))
        input.foreach(c => {
            c match {
                case '<' => x -= 1
                case 'v' => y -= 1
                case '>' => x += 1
                case '^' => y += 1
            }
            coords.add((x, y))
        })
        println(s"Houses: ${coords.size }")
    }

    def partTwo() = {
        var real = true
        var x1, y1, x2, y2 = 0
        val coords1: mutable.HashSet[(Int, Int)] = mutable.HashSet()
        val coords2: mutable.HashSet[(Int, Int)] = mutable.HashSet()
        coords1.add((x1, y1))
        coords2.add((x2, y2))
        input.foreach(c => {
            if(real) {
                c match {
                    case '<' => x1 -= 1
                    case 'v' => y1 -= 1
                    case '>' => x1 += 1
                    case '^' => y1 += 1
                }
                coords1.add((x1, y1))
            } else {
                c match {
                    case '<' => x2 -= 1
                    case 'v' => y2 -= 1
                    case '>' => x2 += 1
                    case '^' => y2 += 1
                }
                coords2.add((x2, y2))
            }
            real = !real
        })
        val coords = coords1 ++ coords2
        println(s"Houses: ${coords.size }")
    }
}
