package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main


object Day5 {

    val data = Main.resource("day5.data").getLines().toList

    val vowels = Set('a', 'e', 'i', 'o', 'u')
    val invalidPhrases = Set("ab", "cd", "pq", "xy")

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        var count = 0
        data.foreach(line => {
            val tripleVowel = line.count(vowels.contains) >= 3
            val doubleChar = (0 until line.length).exists(i => line.slice(i, i + 1) == line.slice(i + 1, i + 2))
            val noInvalids = !invalidPhrases.exists(line.contains)
            if(tripleVowel && doubleChar && noInvalids) {
                count += 1
            }
        })
        println(s"Nice String Count: $count")
    }

    def partTwo() = {
        var count = 0
        data.foreach(line => {
            val sandwich = (0 until line.length - 2).exists(i => line(i) == line(i + 2))
            val twoCharPairs = (0 until line.length - 1).exists(i => (line.drop(i + 1) lastIndexOf line.slice(i, i + 2)) > 0)
            if(sandwich && twoCharPairs) {
                count += 1
            }
        })
        println(s"Nice String Count: $count")
    }

}
