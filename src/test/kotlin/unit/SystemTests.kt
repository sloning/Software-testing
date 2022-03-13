package unit

import math.System
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class SystemTests {
    companion object {
        @JvmStatic
        private fun getValues(): Stream<Pair<Double, Double>> =
            Stream.of(
                -4.2408 to -0.5,
                Double.NaN to 1.0,
                0.7250 to 2.0
            )
    }

    val system = System()

    @ParameterizedTest
    @MethodSource("getValues")
    fun `DefiniteMathSystem class implementation test`(values: Pair<Double, Double>) =
        assertEquals(values.first, system(values.second), 0.0001)
}
