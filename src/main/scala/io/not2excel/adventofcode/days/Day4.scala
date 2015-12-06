package io.not2excel.adventofcode.days

import java.math.BigInteger
import java.security.MessageDigest

import io.not2excel.adventofcode.template.Template

object Day4 extends Template {

    //    val input = Main.sourceFile("day4.data").getLines().next()
    lazy val input = "bgvyzdsv"
    //It's in day4.data as well

    override def partOne() = {
        val answer = Iterator.from(0).indexWhere(i => hashFiveZeros(input + i))
        println(s"$answer")
    }

    override def partTwo() = {
        val answer = Iterator.from(0).indexWhere(i => hashSixZeros(input + i))
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
