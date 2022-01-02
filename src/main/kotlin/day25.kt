fun day25() {
    val lines: List<String> = readFile("day25.txt")

    day25part1(lines)
    day25part2(lines)
}

fun day25part1(lines: List<String>) {
    val seaFloor = getSeaFloor(lines)

    var noOfMovers: Int
    var steps = 0

    do {
        steps++
        noOfMovers = 0
        noOfMovers += moveEast(seaFloor)
        noOfMovers += moveSouth(seaFloor)
    } while (noOfMovers != 0)

    val answer = steps

    println("25a: $answer")
}

fun day25part2(lines: List<String>) {
    val answer = "---"

    println("25b: $answer")
}

fun moveEast(seaFloor: Array<CharArray>): Int {
    val movers = mutableListOf<Pair<Int, Int>>()

    for (i in seaFloor.indices) {
        for (j in seaFloor[0].indices) {
            if (seaFloor[i][j] == '>') {
                if (j == seaFloor[0].size - 1 && seaFloor[i][0] == '.') {
                    movers.add(Pair(i, j))
                } else if (j < seaFloor[0].size - 1 && seaFloor[i][j + 1] == '.') {
                    movers.add(Pair(i, j))
                }
            }
        }
    }

    movers.forEach {
        seaFloor[it.first][it.second] = '.'
        if (it.second == seaFloor[0].size - 1) {
            seaFloor[it.first][0] = '>'
        } else {
            seaFloor[it.first][it.second + 1] = '>'
        }
    }

    return movers.size
}

fun moveSouth(seaFloor: Array<CharArray>): Int {
    val movers = mutableListOf<Pair<Int, Int>>()

    for (i in seaFloor.indices) {
        for (j in seaFloor[0].indices) {
            if (seaFloor[i][j] == 'v') {
                if (i == seaFloor.size - 1 && seaFloor[0][j] == '.') {
                    movers.add(Pair(i, j))
                } else if (i < seaFloor.size - 1 && seaFloor[i + 1][j] == '.') {
                    movers.add(Pair(i, j))
                }
            }
        }
    }

    movers.forEach {
        seaFloor[it.first][it.second] = '.'
        if (it.first == seaFloor.size - 1) {
            seaFloor[0][it.second] = 'v'
        } else {
            seaFloor[it.first + 1][it.second] = 'v'
        }
    }

    return movers.size
}

fun getSeaFloor(lines: List<String>): Array<CharArray> {
    val seaFloor = Array(lines.size) {CharArray(lines[0].length) {' '} }

    for (i in lines.indices) {
        for (j in 0 until lines[0].length) {
            seaFloor[i][j] = lines[i][j]
        }
    }

    return seaFloor
}