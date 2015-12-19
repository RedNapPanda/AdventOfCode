package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day14 {

    val data = Main.resource("day14.data").getLines().toList
    val DeerRegex = "^(\\w+).+ (\\d+) km\\/s.+ (\\d+) seconds,.+ (\\d+) seconds\\.$".r
    var mappedDeer = Map[String, Deer]()
    data.foreach {
                     case DeerRegex(name, speed, flyTime, restTime) =>
                         mappedDeer += (name -> Deer(name, speed.toInt, flyTime.toInt, restTime.toInt))
                 }
    (1 to 2503).foreach(i => {
        mappedDeer.values.foreach(_.tick())
        findLeads().foreach(d => d.addPoint())
    })

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val max = mappedDeer.values.toList.sortBy(d => d.distance).last
        println(s"Max: ${max.name} - ${max.distance}")
    }

    def partTwo() = {
        val max = mappedDeer.values.toList.sortBy(d => d.points).last
        println(s"Max: ${max.name} - ${max.points}")
    }

    def findLeads() = {
        val max = mappedDeer.values.toList.sortBy(d => d.distance).last.distance
        mappedDeer.values.filter(d => d.distance == max)
    }

    case class Deer(name: String, speed: Int, flyTime: Int, restTime: Int) {
        private var _dist, _points, flyTick, restTick = 0
        private var isFlying = true
        flyTick = flyTime

        def tick() = {
            if(isFlying) {
                flyTick -= 1
                _dist += speed
                if(flyTick <= 0) {
                    restTick = restTime
                    isFlying = false
                }
            } else {
                restTick -= 1
                if(restTick <= 0) {
                    flyTick = flyTime
                    isFlying = true
                }
            }
        }

        def addPoint() = _points += 1

        def isResting = !isFlying

        def distance = _dist

        def points = _points
    }

}
