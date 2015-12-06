package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main
import io.not2excel.adventofcode.traits.Template


object Day5 extends Template {

    lazy val input = Main.sourceFile("day5.data").getLines().toList

    lazy val vowels = Set('a', 'e', 'i', 'o', 'u')
    lazy val invalidPhrases = Set("ab", "cd", "pq", "xy")

    override def partOne() = {
        var count = 0
        input.foreach(line => {
            val tripleVowel = line.count(vowels.contains) >= 3
            val doubleChar = (0 until line.length).exists(i => line.slice(i, i + 1) == line.slice(i + 1, i + 2))
            val noInvalids = !invalidPhrases.exists(line.contains)
            if(tripleVowel && doubleChar && noInvalids) {
                count += 1
            }
        })
        println(s"Nice String Count: $count")
    }

    override def partTwo() = {
        var count = 0
        input.foreach(line => {
            val sandwich = (0 until line.length - 2).exists(i => line(i) == line(i + 2))
            val twoDoubleChars = (0 until line.length - 1).exists(i => (line.drop(i + 1) lastIndexOf line.slice(i, i + 2)) > 0)
            if(sandwich && twoDoubleChars) {
                count += 1
            }
        })
        println(s"Nice String Count: $count")
    }

}
