import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day18KtTest {
     @Test
     fun testExplodeWithNoLeftNumbers() {
         assertEquals("[[[[0,9],2],3],4]", explodeSnailFishNumber("[[[[[9,8],1],2],3],4]"))
     }

    @Test
    fun testExplodeWithNoRightNumbers() {
        assertEquals("[7,[6,[5,[7,0]]]]", explodeSnailFishNumber("[7,[6,[5,[4,[3,2]]]]]"))
    }

    @Test
    fun testExplodeWithNumbersInBothDirections() {
        assertEquals("[[6,[5,[7,0]]],3]", explodeSnailFishNumber("[[6,[5,[4,[3,2]]]],1]"))
    }

    @Test
    fun testExplodeWithNoImmediateNumberToTheLeft() {
        assertEquals("[3,[[[0,7],5],6]]", explodeSnailFishNumber("[1,[[[[2,3],4],5],6]]"))
    }

    @Test
    fun testAdvancedExplode() {
        assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", explodeSnailFishNumber("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"))

        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", explodeSnailFishNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"))
    }

    @Test
    fun testExampleExplode() {
        assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", explodeSnailFishNumber("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]"))
    }

    @Test
    fun testProblematicExplode() {
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[17,[[11,9],[11,0]]]]", explodeSnailFishNumber("[[[[4,0],[5,4]],[[7,7],[0,[6,7]]]],[10,[[11,9],[11,0]]]]"))
    }

    @Test
    fun testSplitNumber() {
        assertEquals("[7,8]", splitNumber(15))
    }

    @Test
    fun testSplit() {
        assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", splitSnailFishNumber("[[[[0,7],4],[15,[0,13]]],[1,1]]"))
    }

    @Test
    fun testAddWithReduce() {
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", addSnailFishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]"))
    }

    @Test
    fun testAddWithReduceExample() {
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", addSnailFishNumber(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"
        ))
    }

    @Test
    fun testSumListOfSnailFishNumbers() {
        assertEquals(
            "[[[[1,1],[2,2]],[3,3]],[4,4]]",
            sumListOfSnailFishNumbers(listOf(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]"
            ))
        )
    }

    @Test
    fun testSumListOfSnailFishNumbersThatRequiresReduce() {
        assertEquals(
            "[[[[3,0],[5,3]],[4,4]],[5,5]]",
            sumListOfSnailFishNumbers(listOf(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]"
            ))
        )
    }

    @Test
    fun testSumListOfSnailFishNumbersThatRequiresSeveralReduces() {
        assertEquals(
            "[[[[5,0],[7,4]],[5,5]],[6,6]]",
            sumListOfSnailFishNumbers(listOf(
                "[1,1]",
                "[2,2]",
                "[3,3]",
                "[4,4]",
                "[5,5]",
                "[6,6]"
            ))
        )
    }

    @Test
    fun testSumListOfSnailFishNumbersLargeExample() {
        assertEquals(
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]",
            sumListOfSnailFishNumbers(listOf(
                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
                "[7,[5,[[3,8],[1,4]]]]",
                "[[2,[2,2]],[8,[8,1]]]",
                "[2,9]",
                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
                "[[[5,[7,4]],7],1]",
                "[[[[4,2],2],6],[8,7]]"
            ))
        )
    }
}
