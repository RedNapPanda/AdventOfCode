package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

object Day8 {

    val data = Main.resource("day8.data").getLines().toList

    val hexPattern = "\\\\x[0-9a-f]{2}".r

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val counts = (0 /: data) ((i, s) => i + (s.length - decode(s).length))
        println(s"Counts: $counts")
    }

    def decode(s: String) = {
        var r = s.substring(1, s.length - 1).replace("\\\\", "\\").replace("\\\"", "\"")
        hexPattern.findAllIn(r).foreach(s => r = r.replace(s, hexToInt(s).toChar.toString))
        r
    }

    def hexToInt(str: String) = {
        if(str.startsWith("\\x")) Integer.parseInt(str.substring(2), 16)
        else Integer.parseInt(str, 16)
    }

    def partTwo() = {
        val test = List("\"\"", "\"abc\"", "\"aaa\\\"aaa\"", "\"\\x27\"")
        val counts = (0 /: data) ((i, s) => i + (encode(s).length - s.length))
        println(s"Counts: $counts")
    }

    def encode(s: String) = {
        "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""
    }
}
