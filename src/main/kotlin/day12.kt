fun day12() {
    val lines: List<String> = readFile("day12.txt")

    day12part1(lines)
    day12part2(lines)
}

fun day12part1(lines: List<String>) {
    val caves = initCaveMap(lines)
    val path: MutableList<Cave> = mutableListOf()
    val paths: MutableList<List<Cave>> = mutableListOf()

    findPath(caves, caves.find { it.name == "start" }!!, path, paths)

    val answer = paths.size

    println("12a: $answer")
}

fun day12part2(lines: List<String>) {

    val caves = initCaveMap(lines)
    val path = Path(mutableListOf(), false)
    val paths: MutableList<List<Cave>> = mutableListOf()

    findPath2(caves, caves.find { it.name == "start" }!!, path, paths)

    val answer = paths.size

    println("12b: $answer")
}

fun findPath(caves: List<Cave>, currentCave: Cave, currentPath: List<Cave>, paths: MutableList<List<Cave>>) {
    if (currentPath.contains(currentCave) && "^[a-z]+$".toRegex().matches(currentCave.name)) {
        return
    }

    if (currentCave.name == "end") {
        paths.add(currentPath.plus(currentCave))
        return
    }

    currentCave.neighbors.forEach { neighbor ->
        findPath(caves, caves.find { it.name == neighbor }!!, currentPath.plus(currentCave), paths)
    }
}

fun findPath2(caves: List<Cave>, currentCave: Cave, currentPath: Path, paths: MutableList<List<Cave>>) {
    if (currentPath.currentPath.contains(currentCave) && "^[a-z]+$".toRegex().matches(currentCave.name)) {
        if ((currentCave.name == "start" || currentCave.name == "end") || currentPath.smallCaveVisitedTwice) {
            return
       } else {
           currentPath.smallCaveVisitedTwice = true
       }
    }

    if (currentCave.name == "end") {
        paths.add(currentPath.currentPath.plus(currentCave))
        return
    }

    currentCave.neighbors.forEach { neighbor ->
        findPath2(caves, caves.find { it.name == neighbor }!!, Path(currentPath.currentPath.plus(currentCave), currentPath.smallCaveVisitedTwice), paths)
    }
}

fun initCaveMap(lines: List<String>): MutableList<Cave> {
    val caves: MutableList<Cave> = mutableListOf()
    val cavePairs = lines.map { Pair(it.split("-")[0], it.split("-")[1]) }

    cavePairs.forEach {
        addNeighbor(it.first, it.second, caves)
        addNeighbor(it.second, it.first, caves)
    }

    return caves
}

fun addNeighbor(name: String, neighbor: String, caves: MutableList<Cave>) {
    if (caves.map { cave -> cave.name }.contains(name)) {
        caves.find { cave -> cave.name == name }?.neighbors?.add(neighbor)
    } else {
        caves.add(Cave(name, mutableListOf(neighbor)))
    }
}

data class Cave(val name: String, val neighbors: MutableList<String>)

data class Path(val currentPath: List<Cave>, var smallCaveVisitedTwice: Boolean)