package io.not2excel.adventofcode.days

import java.math.BigInteger
import java.security.MessageDigest

object Day4 {

    //    val input = Main.sourceFile("day4.data").getLines().next()
    val input = "bgvyzdsv"
    //It's in day4.data as well
    val fiveZeros = "00000"
    val sixZeros = "000000"

    def main(args: Array[String]): Unit = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        val answer = Iterator.from(0).indexWhere(i => hashFiveZeros(input + i))
        println(s"$answer")
    }

    def partTwo() = {
        val answer = Iterator.from(0).indexWhere(i => hashSixZeros(input + i))
        println(s"$answer")
    }

    def hashFiveZeros(s: String) = md5Hash(s) startsWith fiveZeros

    def hashSixZeros(s: String) = md5Hash(s) startsWith sixZeros

    def md5Hash(s: String) = {
        val msgDigest = MessageDigest.getInstance("MD5")
        msgDigest.reset()
        msgDigest.update(s.getBytes())
        new BigInteger(1, msgDigest.digest()).toString(16).reverse.padTo(32, '0').reverse
    }
}
