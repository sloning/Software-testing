package unit

import CSVWriter
import math.Cos
import math.Cot
import math.Sec
import math.Sin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import writeFuncSeries
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.Test

class TrigonometricFunctionsTests {
    companion object {
        @JvmStatic
        private fun getCosValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                -1.0 to -PI,
                0.0 to (-PI / 2),
                0.7316 to -0.75,
                1.0 to 0.0,
                0.0 to (PI / 2),
                0.3623 to 1.2,
                -1.0 to PI,
            )

        @JvmStatic
        private fun getSinValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                0.0 to -PI,
                -1.0 to (-PI / 2),
                -0.6816 to -0.75,
                0.0 to 0.0,
                1.0 to (PI / 2),
                0.9320 to 1.2,
                0.0 to PI,
            )

        @JvmStatic
        private fun getCotValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                0.0 to (-PI / 2),
                -1.0734 to -0.75,
                Double.NaN to 0.0,
                0.0 to (PI / 2),
                0.3887 to 1.2,
            )

        @JvmStatic
        private fun getSecValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                -1.0 to -PI,
                1.1105 to -0.45,
                1.0 to 0.0,
                1.1658 to 0.54,
                -1.0 to PI,
                Double.POSITIVE_INFINITY to (-PI / 2),
                Double.POSITIVE_INFINITY to (PI / 2),
            )
    }

    val cos = Cos()
    val sin = Sin()
    val cot = Cot()
    val sec = Sec()

    @ParameterizedTest
    @MethodSource("getSinValues")
    fun `sin(x) test (root function)`(values: Pair<Double, Double>) =
        assertEquals(values.first, sin(values.second), 0.0001)

    @Test
    fun `Manual test sin(from -1 to 1) series`() =
        CSVWriter("reports/sin").writeFuncSeries(sin, -PI, 0.1, PI)

    @ParameterizedTest
    @MethodSource("getCosValues")
    fun `cos(x) test (based on sin(x) implementation)`(values: Pair<Double, Double>) =
        assertEquals(values.first, cos(values.second), 0.0001)

    @Test
    fun `Manual test cos(from -1 to 1) series`() =
        CSVWriter("reports/cos").writeFuncSeries(cos, -PI, 0.1, PI)

    @ParameterizedTest
    @MethodSource("getCotValues")
    fun `cot(x) test (based on cos(x) and sin(x) implementation)`(values: Pair<Double, Double>) =
        assertEquals(values.first, cot(values.second), 0.0001)

    @Test
    fun `Manual test cot(from -1 to 1) series`() =
        CSVWriter("reports/cot").writeFuncSeries(cot, -PI, 0.1, PI)

    @ParameterizedTest
    @MethodSource("getSecValues")
    fun `sec(x) test (based on cos(x) implementation)`(values: Pair<Double, Double>) =
        assertEquals(values.first, sec(values.second), 0.0001)

    @Test
    fun `Manual test sec(from -1 to 1) series`() =
        CSVWriter("reports/sec").writeFuncSeries(sec, -PI, 0.1, PI)
}
