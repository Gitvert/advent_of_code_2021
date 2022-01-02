fun day19() {
    val lines: List<String> = readFile("day19.txt")

    day19part1(lines)
    day19part2(lines)
}

fun day19part1(lines: List<String>) {
    val scanner0 = initScanner0(lines.subList(0, lines.indexOf("--- scanner 1 ---")))
    val unknownRotationScanners = initUnknownRotationScanners(lines.subList(lines.indexOf("--- scanner 1 ---"), lines.size))

    findAllBeacons(scanner0, unknownRotationScanners)

    val answer = scanner0.beacons.size

    println("19a: $answer")
}

fun day19part2(lines: List<String>) {
    val scanner0 = initScanner0(lines.subList(0, lines.indexOf("--- scanner 1 ---")))
    val unknownRotationScanners = initUnknownRotationScanners(lines.subList(lines.indexOf("--- scanner 1 ---"), lines.size))

    findAllBeacons(scanner0, unknownRotationScanners)

    val scannerPositions = mutableListOf<Position>()
    scannerPositions.add(scanner0.position)

    unknownRotationScanners.forEach {
        scannerPositions.add(it.position!!)
    }

    var maxManhattanDistance = -1

    for (i in 0 until scannerPositions.size) {
        for (j in 0 until scannerPositions.size) {
            val manhattanDistance = findManhattanDistance(scannerPositions[i], scannerPositions[j])
            if (manhattanDistance > maxManhattanDistance) {
                maxManhattanDistance = manhattanDistance
            }
        }
    }

    val answer = maxManhattanDistance

    println("19b: $answer")
}

fun findManhattanDistance(s1: Position, s2: Position): Int {
    return kotlin.math.abs(s1.x - s2.x) + kotlin.math.abs(s1.y - s2.y) + kotlin.math.abs(s1.z - s2.z)
}

fun findAllBeacons(scanner0: Scanner, unknownRotationScanners: List<UnknownRotationScanner>) {
    findBeaconDistances(scanner0)
    unknownRotationScanners.forEach { findUnknownRotationScannerBeaconDistances(it) }

    for (element in unknownRotationScanners) {
        findOverlappers(scanner0, element)
    }

    for (element in unknownRotationScanners) {
        findOverlappers(scanner0, element)
    }

    for (element in unknownRotationScanners) {
        findOverlappers(scanner0, element)
    }

    for (element in unknownRotationScanners) {
        findOverlappers(scanner0, element)
    }
}

fun findRelativePosition(o1: List<Beacon>, o2: List<Beacon>, s1: Scanner, beaconsToAdd: Set<Beacon>): Position {
    val xAdd = mutableListOf<Int>()
    val xSubtract = mutableListOf<Int>()
    val yAdd = mutableListOf<Int>()
    val ySubtract = mutableListOf<Int>()
    val zAdd = mutableListOf<Int>()
    val zSubtract = mutableListOf<Int>()

    for (i in o1.indices) {
        xAdd.add(o1[i].position.x + o2[i].position.x)
        xSubtract.add(o1[i].position.x - o2[i].position.x)
        yAdd.add(o1[i].position.y + o2[i].position.y)
        ySubtract.add(o1[i].position.y - o2[i].position.y)
        zAdd.add(o1[i].position.z + o2[i].position.z)
        zSubtract.add(o1[i].position.z - o2[i].position.z)
    }

    val addX = xAdd.all { it == xAdd.first() }
    val addY = yAdd.all { it == yAdd.first() }
    val addZ = zAdd.all { it == zAdd.first() }

    val x = if (addX) { xAdd.first() } else { xSubtract.first() }
    val y = if (addY) { yAdd.first() } else { ySubtract.first() }
    val z = if (addZ) { zAdd.first() } else { zSubtract.first() }

    val newPosition = Position(
        x + s1.position.x,
        y + s1.position.y,
        z + s1.position.z
    )

    beaconsToAdd.forEach {
        s1.beacons.add(Beacon(
            Position(
                if (addX) { newPosition.x - it.position.x } else { newPosition.x + it.position.x },
                if (addY) { newPosition.y - it.position.y } else { newPosition.y + it.position.y },
                if (addZ) { newPosition.z - it.position.z } else { newPosition.z + it.position.z }
            ),
            it.distances
        ))
    }

    return newPosition
}

fun findOverlappers(s1: Scanner, s2: UnknownRotationScanner) {
    val overlappers0 = mutableSetOf<Beacon>()
    val overlappers1 = mutableSetOf<Beacon>()
    val beaconsToAdd = mutableSetOf<Beacon>()

    s1.beacons.forEach { outer ->
        s2.possibleBeacons.forEach { middle ->
            middle.forEach { inner ->
                val intersections = inner.distances.intersect(outer.distances.toSet())
                if (intersections.size > 10 && !overlappers0.contains(outer)) {
                    overlappers0.add(outer)
                    overlappers1.add(inner)
                    beaconsToAdd.addAll(middle)
                }
            }
        }

    }

    if (overlappers0.size > 1) {
        s2.position = findRelativePosition(overlappers0.toList(), overlappers1.toList(), s1, beaconsToAdd)
    }
}

fun findBeaconDistances(scanner: Scanner) {
    scanner.beacons.forEachIndexed { i, outer ->
        scanner.beacons.forEachIndexed inner@ { j, inner ->
            if (i == j) {
                return@inner
            }
            val xDist = kotlin.math.abs(outer.position.x - inner.position.x)
            val yDist = kotlin.math.abs(outer.position.y - inner.position.y)
            val zDist = kotlin.math.abs(outer.position.z - inner.position.z)
            outer.distances.add(BeaconDistance(xDist, yDist, zDist))
        }
    }
}

fun findUnknownRotationScannerBeaconDistances(scanner: UnknownRotationScanner) {
    scanner.possibleBeacons.forEach {
        it.forEachIndexed { i, outer ->
            it.forEachIndexed inner@ { j, inner ->
                if (i == j) {
                    return@inner
                }
                val xDist = kotlin.math.abs(outer.position.x - inner.position.x)
                val yDist = kotlin.math.abs(outer.position.y - inner.position.y)
                val zDist = kotlin.math.abs(outer.position.z - inner.position.z)
                outer.distances.add(BeaconDistance(xDist, yDist, zDist))
            }
        }
    }
}

fun initUnknownRotationScanners(lines: List<String>): List<UnknownRotationScanner> {
    val scanners = mutableListOf<UnknownRotationScanner>()
    var currentScanner: UnknownRotationScanner? = null
    var beaconSet = mutableSetOf<Beacon>()

    lines.forEachIndexed { index, it ->
        if (it.contains("scanner")) {
            if (currentScanner != null) {
                currentScanner!!.possibleBeacons = mutableListOf(beaconSet)
                beaconSet = mutableSetOf()
            }

            val id = Integer.valueOf(it.substring("\\d+".toRegex().find(it)!!.range))
            currentScanner = UnknownRotationScanner(id, mutableListOf(mutableSetOf()), null )
            scanners.add(currentScanner!!)
        } else if (it.isNotEmpty()) {
            val coordinates = it.split(",")
            val xCoordinate = Integer.valueOf(coordinates[0])
            val yCoordinate = Integer.valueOf(coordinates[1])
            val zCoordinate = Integer.valueOf(coordinates[2])

            beaconSet.add(Beacon(Position(xCoordinate, yCoordinate, zCoordinate), mutableListOf()))
        }
    }

    currentScanner!!.possibleBeacons = mutableListOf(beaconSet)

    scanners.forEach {

        var additionalBeaconSet = mutableSetOf<Beacon>()
        it.possibleBeacons.toList()[0].forEach { beacon ->
            additionalBeaconSet.add(Beacon(Position(beacon.position.x, beacon.position.z, beacon.position.y), mutableListOf()))
        }
        it.possibleBeacons.add(additionalBeaconSet)

        additionalBeaconSet = mutableSetOf()
        it.possibleBeacons.toList()[0].forEach { beacon ->
            additionalBeaconSet.add(Beacon(Position(beacon.position.y, beacon.position.x, beacon.position.z), mutableListOf()))
        }
        it.possibleBeacons.add(additionalBeaconSet)

        additionalBeaconSet = mutableSetOf()
        it.possibleBeacons.toList()[0].forEach { beacon ->
            additionalBeaconSet.add(Beacon(Position(beacon.position.y, beacon.position.z, beacon.position.x), mutableListOf()))
        }
        it.possibleBeacons.add(additionalBeaconSet)

        additionalBeaconSet = mutableSetOf()
        it.possibleBeacons.toList()[0].forEach { beacon ->
            additionalBeaconSet.add(Beacon(Position(beacon.position.z, beacon.position.x, beacon.position.y), mutableListOf()))
        }
        it.possibleBeacons.add(additionalBeaconSet)

        additionalBeaconSet = mutableSetOf()
        it.possibleBeacons.toList()[0].forEach { beacon ->
            additionalBeaconSet.add(Beacon(Position(beacon.position.z, beacon.position.y, beacon.position.x), mutableListOf()))
        }
        it.possibleBeacons.add(additionalBeaconSet)
    }

    return scanners
}

fun initScanner0(lines: List<String>): Scanner {
    val scanner0 = Scanner(0, mutableSetOf(), Position(0, 0, 0))

    lines.forEach {
        if (!it.contains("scanner")) {
            if (it.isEmpty()) {
                return scanner0
            } else {
                val coordinates = it.split(",")
                val xCoordinate = Integer.valueOf(coordinates[0])
                val yCoordinate = Integer.valueOf(coordinates[1])
                val zCoordinate = Integer.valueOf(coordinates[2])

                scanner0.beacons.add(Beacon(Position(xCoordinate, yCoordinate, zCoordinate), mutableListOf()))
            }
        }
    }

    return scanner0
}

data class Position(val x: Int, val y: Int, val z: Int)

data class BeaconDistance(val x: Int, val y: Int, val z: Int)

class Beacon(val position: Position, var distances: MutableList<BeaconDistance>) {
    override fun equals(other: Any?): Boolean {
        return this::class == other!!::class && this.position == (other as Beacon).position
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }
}

data class Scanner(val id: Int, val beacons: MutableSet<Beacon>, var position: Position)

data class UnknownRotationScanner(val id: Int, var possibleBeacons: MutableList<MutableSet<Beacon>>, var position: Position?)