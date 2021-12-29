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
}
