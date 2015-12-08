package io.not2excel.adventofcode.days

import io.not2excel.adventofcode.Main

import scala.collection.immutable.HashMap
import scala.collection.mutable

object Day7 {

    val data = Main.resource("day7.data").getLines().toList

    val SingleInput = "^(\\w+) -> (\\w+)$".r
    val NotSingleInput = "^NOT (\\w+) -> (\\w+)$".r
    val MultiInput = "^(\\w+) (\\w+) (\\w+) -> (\\w+)$".r

    var nodeMap: Map[String, Node] = HashMap()

    var hasPrinted: mutable.Set[String] = mutable.HashSet()

    val test = List("123 -> x",
                    "456 -> y",
                    "x AND y -> d",
                    "x OR y -> e",
                    "x LSHIFT 2 -> f",
                    "y RSHIFT 2 -> g",
                    "NOT x -> h",
                    "NOT y -> i"
                   )

    val testMap = HashMap(("d", 72),
                          ("e", 507),
                          ("f", 492),
                          ("g", 114),
                          ("h", 65412),
                          ("i", 65079),
                          ("x", 123),
                          ("y", 456))

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    private var aSignal = 0

    def partOne() = {
        data.foreach(parseNode)
        val aNode = nodeMap.get("a")
        nodeMap.values.foreach(n => if(!n.isInitialized) n.calculate())
        if(aNode.isDefined) {
            aSignal = aNode.get.calculate()
            println(s"NodeKey A: $aSignal")
        }
    }

    def partTwo() = {
        nodeMap.values.foreach(n => n.reset())
        val bNode = nodeMap.get("b")
        if(bNode.isDefined) bNode.get.overrideNode(aSignal.toString)
        val aNode = nodeMap.get("a")
        nodeMap.values.foreach(n => if(!n.isInitialized) n.calculate())
        if(aNode.isDefined) {
            aSignal = aNode.get.calculate()
            println(s"NodeKey A: $aSignal")
        }
    }

    @throws[IllegalArgumentException]
    def parseNode(s: String) = {
        var node: Node = null
        s match {
            case SingleInput(input, nodeKey) => node = SingleNode(s, input, nodeKey)
            case NotSingleInput(input, nodeKey) => node = SingleNode(s, input, nodeKey, not = true)
            case MultiInput(left, op, right, nodeKey) => node = MultiNode(s, left, right, op, nodeKey)
            case _ => throw new IllegalArgumentException("Failed to match line to any pattern.")
        }
        nodeMap += (node.nodeKey -> node)
        node
    }

    implicit class UnsignedInt(i: Int) {
        def not = ~i

        def unsigned = i.toChar.toInt

        def unsignedNot = (~i).toChar.toInt

        def and(j: Int) = (i & j).toChar.toInt

        def or(j: Int) = (i | j).toChar.toInt

        def lShift(j: Int) = (i << j).toChar.toInt

        def rShift(j: Int) = (i >> j).toChar.toInt
    }

    trait Node {

        val nodeKey: String = ""

        protected var _value = 0

        def value = _value

        protected var _initialized = false

        def isInitialized = _initialized

        final def reset() = {
            _initialized = false
            hasPrinted.remove(nodeKey)
        }

        final def calculate(): Int = {
            if(!isInitialized) initialize()
            value
        }

        def initialize(): Unit

        def overrideNode(s: String) = {}

        def isConstant(s: String) = s forall Character.isDigit

        def nodeValueOf(s: String): Int = {
            if(isConstant(s)) s.toInt
            else {
                val node = nodeMap.get(s)
                if(node.isDefined) node.get.calculate()
                else throw new NodeException(s"Invalid key: $s")
            }
        }
    }

    case class SingleNode(private var line: String, private var s: String,
                          override val nodeKey: String, not: Boolean = false)
        extends Node {

        override def initialize(): Unit = {
            var i = nodeValueOf(s)
            if(not) i = i.not
            if(i < 0) i = i.unsigned
            _value = i
            _initialized = true
            if(!hasPrinted.contains(nodeKey)) {
                hasPrinted add nodeKey
                println(s"[$nodeKey] $line = $i")
            }
        }

        override def overrideNode(n: String) = {
            line = line.replace(s, n)
            s = n
        }
    }

    case class MultiNode(line: String, left: String, right: String, op: String,
                         override val nodeKey: String)
        extends Node {

        override def initialize(): Unit = {
            val leftVal = nodeValueOf(left)
            val rightVal = nodeValueOf(right)
            var i = 0
            op match {
                case "AND" => i = leftVal and rightVal
                case "OR" => i = leftVal or rightVal
                case "LSHIFT" => i = leftVal lShift rightVal
                case "RSHIFT" => i = leftVal rShift rightVal
            }
            if(i < 0) i = i.unsigned
            _value = i
            _initialized = true
            if(!hasPrinted.contains(nodeKey)) {
                hasPrinted add nodeKey
                println(s"[$nodeKey] $line = $i")
            }
        }
    }

    class NodeException(msg: String) extends Exception(msg)

}
