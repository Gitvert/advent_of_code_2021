fun day13() {
    val lines: List<String> = readFile("day13.txt")

    day13part1(lines)
    day13part2(lines)
}

fun day13part1(lines: List<String>) {
    var paper = getPaper(lines)

    val foldInstructions = getFoldInstructions(lines)

    paper = fold(paper, foldInstructions[0])

    val answer = paper.map { row -> row.filter { it == '#' } }.flatten().size

    println("13a: $answer")
}

fun day13part2(lines: List<String>) {
    var paper = getPaper(lines)

    val foldInstructions = getFoldInstructions(lines)

    foldInstructions.forEach {
        paper = fold(paper, it)
    }

    println("13b:")
    printPaper(paper)
}

fun printPaper(paper: Array<CharArray>) {
    paper.forEach { row ->
        println(row.map { it.toString() }.reduce { acc, c -> acc+ c})
    }
}

fun fold(paper: Array<CharArray>, foldInstruction: Pair<Char, Int>): Array<CharArray> {
    return if (foldInstruction.first == 'y') {
        for (x in paper.indices) {
            for (y in 0 until paper[0].size) {
                if (x > foldInstruction.second && paper[x][y] == '#') {
                    paper[kotlin.math.abs(paper.size - 1 - x)][y] = '#'
                }
            }
        }

        paper.slice(0 until foldInstruction.second).toTypedArray()
    } else {
        for (x in paper.indices) {
            for (y in 0 until paper[0].size) {
                if (y > foldInstruction.second && paper[x][y] == '#') {
                    paper[x][kotlin.math.abs(paper[0].size -1 - y)] = '#'
                }
            }
        }

        paper.map { it.slice(0 until foldInstruction.second).toCharArray() }.toTypedArray()
    }
}

fun getPaper(lines: List<String>): Array<CharArray> {
    val dotLocations = lines
        .filter { it.isNotEmpty() && it[0].isDigit() }
        .map { Pair(Integer.valueOf(it.split(",")[0]), Integer.valueOf(it.split(",")[1])) }

    val xSize = dotLocations.map { it.first }.maxOf { it } + 1
    val ySize = dotLocations.map { it.second }.maxOf { it } + 1

    val paper = Array(ySize) { CharArray(xSize) { ' ' } }

    dotLocations.forEach {
        paper[it.second][it.first] = '#'
    }

    return paper
}

fun getFoldInstructions(lines: List<String>): List<Pair<Char, Int>> {
    return lines
        .filter { it.isNotEmpty() && it.startsWith("fold") }
        .map { Pair(it.split("=")[0].lastOrNull()!!, Integer.valueOf(it.split("=")[1])) }
}