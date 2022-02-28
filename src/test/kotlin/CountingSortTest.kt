import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CountingSortTest {
    private fun <T: Comparable<T>> List<T>.bubbleSort(): List<T> =
        this.toMutableList().apply {
            for (i in 0..this.size - 2)
                for (j in 0..this.size - i - 2)
                    if (this[j] > this[j + 1])
                        this[j] = this[j + 1].also { this[j + 1] = this[j] } }

    companion object {
        @JvmStatic
        fun getArraysForManualTest(): Stream<Pair<List<Int>, List<Int>>> {
            return Stream.of(
                Pair(
                    listOf(10, 23, 45, 15, 5, -2, 2, 7),
                    listOf(-2, 2, 5, 7, 10, 15, 23, 45)
                ),
                Pair(
                    listOf(-8, -7, 10, 4, -9, 23, 5, 4, 3),
                    listOf(-9, -8, -7, 3, 4, 4, 5, 10, 23)
                ),
                Pair(
                    listOf(0, 1),
                    listOf(0, 1)
                ),
                Pair(
                    listOf(),
                    listOf()
                ),
            );
        }
    }

    @ParameterizedTest
    @MethodSource("getArraysForManualTest")
    fun `Counting sort test versus manual sort`() {
//        Assertions.assertEquals(, countingSort())
    }

    @Test
    fun `Counting sort test versus bubble sort`() {
        val numbers = ArrayList<Int>()
        for (i in 0 .. 30) {
            val number = (0..1024).shuffled().first() // генерация случайного числа в районе от 0 до 1024
            numbers.add(number)
        }
        val sortedNumbers = countingSort(numbers)
        Assertions.assertEquals(numbers.bubbleSort(), sortedNumbers)
    }
}