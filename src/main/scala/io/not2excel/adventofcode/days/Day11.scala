package io.not2excel.adventofcode.days

object Day11 {

    //        val data = Main.resource("day11.data").getLines().toList
    var data = "vzbxkghb"
    //It's in day11.data as well
    val FirstCheck = "(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)".r
    val invalidChars = List("i", "l", "o")
    val ThirdCheck = "(.)\\1.*(.)\\2".r

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        while(!checkOne(data) || !checkTwo(data) || !checkThree(data)) {
            data = increment(data)
        }
        println(s"Next Pass: $data")
    }

    def partTwo() = {
        data = increment(data)
        while(!checkOne(data) || !checkTwo(data) || !checkThree(data)) {
            data = increment(data)
        }
        println(s"Next Pass: $data")
    }

    def checkOne(s: String) = FirstCheck.findFirstIn(s).isDefined

    def checkTwo(s: String) = !invalidChars.exists(s.contains)

    def checkThree(s: String) = ThirdCheck.findFirstIn(s).isDefined

    def increment(s: String): String = {
        val l = s.length
        val c = s.charAt(l - 1)
        if(c == 'z') {
            if(l > 1) increment(s.substring(0, l - 1)) + 'a'
            else "aa"
        }
        else s.substring(0, l - 1) + (c + 1).toChar
    }
}
