package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

import scala.collection.immutable.HashMap

object Day13 {

    val data = Main.resource("day13.data").getLines().toList
    val SeatingRegex = "^(\\w+).+ (lose|gain) (\\d+).+ (\\w+)\\.$".r
    var mappedSeating = HashMap[String, Map[String, Int]]()
    data.foreach {
                     case SeatingRegex(seat1, sign, happiness, seat2) =>
                         val i = sign match {
                             case "lose" => -1
                             case _ => 1
                         }
                         val h = happiness.toInt * i
                         addSeat(seat1, seat2, h)
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
        println(s"Max: ${calcSeatHappiness.max}")
    }

    def partTwo() = {
        val m = "me"
        mappedSeating.keySet.foreach(s => {
            addSeat(m, s, 0)
            addSeat(s, m, 0)
        })
        println(s"Max: ${calcSeatHappiness.max}")
    }

    def calcSeatHappiness = {
        mappedSeating.keySet.toSeq.permutations.map(s => {
            var i = 0
            s.foreach(s1 => {
                val n = nextTo(s, s1)
                i += mappedSeating.get(s1).get.get(n._1).get + mappedSeating.get(s1).get.get(n._2).get
            })
            i
        })
    }

    def nextTo(list: Seq[String], s: String): (String, String) = {
        val w = window[String](list.toList)
        for(l: List[Option[String]] <- w) {
            if(l(1).get.equalsIgnoreCase(s)) {
                val prev = l.head match {
                    case Some(s1) => s1
                    case None => list.last
                }
                val next = l(2) match {
                    case Some(s1) => s1
                    case None => list.head
                }
                return (prev, next)
            }
        }
        ("", "")
    }

    def window[A](l: List[A]): Iterator[List[Option[A]]] =
        (None :: l.map(Some(_)) ::: List(None)) sliding 3

    def addSeat(seat1: String, seat2: String, h: Int) = {
        mappedSeating.get(seat1) match {
            case Some(m) => mappedSeating += (seat1 -> (m + (seat2 -> h)))
            case _ => mappedSeating += (seat1 -> Map(seat2 -> h))
        }
    }
}
