package io.not2excel.adventofcode.days

object Day10 {

    //    val data = Main.resource("day10.data").getLines().toList
    val data = "1113122113"
    //It's in day10.data as well
    var result = data

    def main(args: Array[String]) = {
        println("Part 1")
        partOne()
        println("======")
        println("Part 2")
        partTwo()
        println("======")
    }

    def partOne() = {
        var result = this.result
        (0 until 40).foreach(i => {
            print(s"$i ")
            result = countDigits(result)
        })
        println(s"\nResult: ${result.length}\n")
        this.result = result
    }

    def partTwo() = {
        var result = this.result
        (0 until 10).foreach(i => {
            print(s"${40 + i} ")
            result = countDigits(result)
        })
        println(s"\nResult: ${result.length}\n")
    }

    def countDigits(data: String) = {
        var count, i = 0
        var n = '0'
        var result = List[String]()
        while(i < data.length) {
            val c = data(i)
            if(n != c) {
                if(count > 0) {
                    result = n.toString :: count.toString :: result
                }
                n = c
                count = 0
            }
            count += 1
            i += 1
        }
        result = n.toString :: count.toString :: result
        result.reverse.mkString
    }
}
