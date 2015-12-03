package io.not2excel.adventofcode

import scala.io.Source

object Main {

    def main(args: Array[String]): Unit = {

    }

    def sourceFile(name: String) = Source.fromInputStream(getClass.getResourceAsStream(s"/$name"))
}
