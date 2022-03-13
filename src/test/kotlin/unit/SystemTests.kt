package unit

import math.System
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class SystemTests {
    companion object {
        @JvmStatic
        private fun getValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                Double.NaN to -PI,
                -808.272 to -2.9,
                -7638.87 to -1.8,
                Double.NaN to (-PI / 2),
                -2730.85 to -1.3,
                -1754.47 to -0.2,
                Double.NaN to 0.0,
                53.137 to 0.0001,
                1.32607 to 0.9999,
                Double.NaN to 1.0,
                1.31559 to 1.01,
                0.72501986 to 2.0
            )
    }

    val system = System()

    @ParameterizedTest
    @MethodSource("getValues")
    fun `DefiniteMathSystem class implementation test`(values: Pair<Double, Double>) =
        assertEquals(values.first, system(values.second), 0.01)
}
