fun day20() {
    val lines: List<String> = readFile("day20.txt")

    day20part1(lines)
    day20part2(lines)
}

fun day20part1(lines: List<String>) {
    val answer = enhanceImage(lines, 2)

    println("20a: $answer")
}

fun day20part2(lines: List<String>) {
    val answer = enhanceImage(lines, 50)

    println("20b: $answer")
}

fun getImage(lines: List<String>): Array<CharArray> {
    val image = Array(500) { CharArray(500) { '.' } }

    for (i in lines.indices) {
        for (j in lines.indices) {
            image[i + 250][j + 250] = lines[i][j]
        }
    }

    return image
}

fun enhanceImage(lines: List<String>, passes: Int): Int {
    val enhancementAlgorithm = lines[0]
    val imageData = lines.subList(2, lines.size)

    val image = getImage(imageData)

    val enhancedImage = Array(500) { CharArray(500) { '.' } }
    val enhancedImage2 = Array(500) { CharArray(500) { '.' } }

    for (enhancementLoop in 0 until passes) {
        val toImage: Array<CharArray>
        val fromImage: Array<CharArray>

        toImage = (if (enhancementLoop % 2 == 0) {enhancedImage} else {enhancedImage2}).clone()
        fromImage = (if (enhancementLoop % 2 != 0) {enhancedImage} else if (enhancementLoop == 0) {image} else {enhancedImage2}).clone()

        for (i in 1..image.size - 2) {
            for (j in 1..image.size - 2) {
                toImage[i][j] = getPixel(fromImage, i, j, enhancementAlgorithm)
            }
        }
    }

    for (i in image.indices) {
        for (j in image.indices) {
            if (i < 100 || j < 100 || i > 400 || j > 400)
                enhancedImage2[i][j] = '.'
        }
    }

    var answer = 0

    enhancedImage2.forEach { row ->
        row.forEach { cell ->
            if (cell == '#') {
                answer++
            }
        }
    }

    return answer
}

fun getPixel(image: Array<CharArray>, xPos: Int, yPos: Int, enhancementAlgorithm: String): Char {
    var binaryNumber = ""

    binaryNumber += if (image[xPos - 1][yPos - 1] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos - 1][yPos] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos - 1][yPos + 1] == '.') {"0"} else {"1"}

    binaryNumber += if (image[xPos][yPos - 1] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos][yPos] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos][yPos + 1] == '.') {"0"} else {"1"}

    binaryNumber += if (image[xPos + 1][yPos - 1] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos + 1][yPos] == '.') {"0"} else {"1"}
    binaryNumber += if (image[xPos + 1][yPos + 1] == '.') {"0"} else {"1"}


    return enhancementAlgorithm[Integer.valueOf(binaryNumber, 2)]
}
