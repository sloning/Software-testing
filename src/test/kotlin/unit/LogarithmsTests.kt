package unit

import CSVWriter
import math.Ln
import math.Log
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import writeFuncSeries
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.random.Random
import kotlin.test.assertEquals

class LogarithmsTests {
    companion object {
        @JvmStatic
        private fun lnValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                Double.NaN to -Random.nextDouble(until = 10.0),
                Double.NaN to 0.0,
                -0.6931 to 0.5,
                0.0 to 1.0,
                0.40546 to 1.5
            )

        @JvmStatic
        private fun logValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                Double.NaN to 0.0,
                -0.3010 to 0.5,
                0.0 to 1.0,
                0.1760 to 1.5
            )
    }

    val ln: Ln = Ln()
    val log: Log = Log()

    @ParameterizedTest
    @MethodSource("lnValues")
    fun `ln(x) series test`(value: Pair<Double, Double>): Unit =
        assertEquals(value.first, ln(value.second), 0.0001)

    @Test
    fun `Manual test ln(from -1 to 1) series`() =
        CSVWriter("reports/ln").writeFuncSeries(ln, -PI, 0.1, PI)

    @ParameterizedTest
    @MethodSource("logValues")
    fun `log(x) by with base test (based on ln(x) implementation inside)`(value: Pair<Double, Double>): Unit =
        assertEquals(value.first, log(value.second, 10), 0.0001)

    @Test
    fun `Manual test log(from -1 to 1) series`() =
        CSVWriter("reports/log").writeFuncSeries(log, -PI, 0.1, PI, arrayOf(5.0))
}