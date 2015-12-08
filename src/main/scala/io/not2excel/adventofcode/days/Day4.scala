package io.not2excel.adventofcode.days

import java.math.BigInteger
import java.security.MessageDigest

import io.not2excel.adventofcode.traits.Template

object Day4 {

    //    val data = Main.sourceFile("day4.data").getLines().next()
    val data = "bgvyzdsv"
    //It's in day4.data as well

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val answer = Iterator.from(0).indexWhere(i => hashFiveZeros(data + i))
        println(s"$answer")
    }

    def partTwo() = {
        val answer = Iterator.from(0).indexWhere(i => hashSixZeros(data + i))
        println(s"$answer")
    }

    def hashFiveZeros(s: String) = md5Hash(s).length <= 27

    def hashSixZeros(s: String) = md5Hash(s).length <= 26

    def md5Hash(s: String) = {
        val msgDigest = MessageDigest.getInstance("MD5")
        msgDigest.reset()
        msgDigest.update(s.getBytes())
        new BigInteger(1, msgDigest.digest()).toString(16)
    }
}
