package integration

import math.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class SystemTests {
    companion object {
        @JvmStatic
        private fun systemValues(): Stream<Pair<Double, Double>> =
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

    val sin: Sin = Mockito.mock(Sin::class.java)
    val ln: Ln = Mockito.mock(Ln::class.java)

    val cos: Cos = Cos().apply { sin }
    val cot: Cot = Cot().apply { cos; sin }
    val sec: Sec = Sec().apply { cos; }
    val csc: Csc = Csc().apply { sin }
    val log: Log = Log().apply { ln }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with sin and ln as mocks`(value: Pair<Double, Double>) {
        Mockito.`when`(sin(value.second)).thenReturn(kotlin.math.sin(value.second))
        Mockito.`when`(ln(value.second)).thenReturn(kotlin.math.ln(value.second))
        val system = System().apply { sin; cos; cot; sec; csc; ln; log }

        assertEquals(value.first, system.invoke(value.second), 0.01)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with sin as mock and real ln`(value: Pair<Double, Double>) {
        Mockito.`when`(sin(value.second)).thenReturn(kotlin.math.sin(value.second))
        val system = System().apply { sin; cos; cot; sec; csc }

        assertEquals(value.first, system.invoke(value.second), 0.01)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with ln as mock and real sin`(value: Pair<Double, Double>) {
        Mockito.`when`(ln(value.second)).thenReturn(kotlin.math.ln(value.second))
        val system = System().apply { ln; log }

        assertEquals(value.first, system.invoke(value.second), 0.01)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with real sin and ln`(value: Pair<Double, Double>) {
        val system = System()
        assertEquals(value.first, system.invoke(value.second), 0.01)
    }
}