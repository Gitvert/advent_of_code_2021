fun day24() {

    val lines: List<String> = readFile("day24.txt")

    day24part1(lines)
    day24part2(lines)
}

var w = 0
var x = 0
var y = 0
var z = 0

fun day24part1(lines: List<String>) {
    val blocks = getBlocks(lines)
    var serialNumber = 99999999999999
    var solved = false
    var tries = 0L

    while (!solved) {
        val numberString = serialNumber.toString()

        if (numberString.contains('0')) {
            serialNumber--
            continue
        }

        x = 0
        y = 0
        z = 0

        blocks.forEachIndexed { index, it ->
            parseBlock(it, Integer.valueOf(numberString[index].toString()))
        }

        if (z == 0) {
            solved = true
        } else {
            serialNumber--
            tries++
        }
    }

    val answer = serialNumber

    println("24a: $answer")
}

fun day24part2(lines: List<String>) {
    val answer = "TBD"

    println("24b: $answer")
}

fun parseBlock(lines: List<String>, numberToTry: Int) {
    w = numberToTry

    lines.filter { !it.startsWith('i') }.forEach {
        parseLine(it)
    }
}

fun parseLine(line: String) {
    val parts = line.split(" ")

    when (parts[0]) {
        "add" -> add(parts[1], parts[2])
        "mul" -> mul(parts[1], parts[2])
        "div" -> div(parts[1], parts[2])
        "mod" -> mod(parts[1], parts[2])
        "eql" -> eql(parts[1], parts[2])
    }
}

fun getBlocks(lines: List<String>): Array<MutableList<String>> {
    val blocks = Array(14) { mutableListOf<String>() }
    var index = -1

    lines.forEach {
        if (it.startsWith('i')) {
            index++
        }

        blocks[index].add(it)
    }

    return blocks
}

fun add(a: String, b: String) {
    when (a) {
        "w" -> w += getActualNumber(b)
        "x" -> x += getActualNumber(b)
        "y" -> y += getActualNumber(b)
        "z" -> z += getActualNumber(b)
    }
}

fun mul(a: String, b: String) {
    when (a) {
        "w" -> w *= getActualNumber(b)
        "x" -> x *= getActualNumber(b)
        "y" -> y *= getActualNumber(b)
        "z" -> z *= getActualNumber(b)
    }
}

fun div(a: String, b: String) {
    val divisor = getActualNumber(b)

    if (divisor == 0) {
        return
    }

    when (a) {
        "w" -> w /= getActualNumber(b)
        "x" -> x /= getActualNumber(b)
        "y" -> y /= getActualNumber(b)
        "z" -> z /= getActualNumber(b)
    }
}

fun mod(a: String, b: String) {
    val mod1 = when (a) {
        "w" -> w
        "x" -> x
        "y" -> y
        "z" -> z
        else -> 0
    }
    val mod2 = getActualNumber(b)

    if (mod1 < 0 || mod2 <= 0) {
        return
    }

    when (a) {
        "w" -> w %= mod2
        "x" -> x %= mod2
        "y" -> y %= mod2
        "z" -> z %= mod2
    }
}

fun eql(a: String, b: String) {
    when (a) {
        "x" -> x = if (x == getActualNumber(b)) { 1 } else { 0 }
        "y" -> y = if (y == getActualNumber(b)) { 1 } else { 0 }
        "z" -> z = if (z == getActualNumber(b)) { 1 } else { 0 }
    }
}

fun getActualNumber(b: String): Int {
    return when (b) {
        "w" -> w
        "x" -> x
        "y" -> y
        "z" -> z
        else -> Integer.valueOf(b)
    }
}