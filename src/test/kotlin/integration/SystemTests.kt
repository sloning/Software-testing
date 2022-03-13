package integration

import math.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.stream.Stream
import kotlin.test.assertEquals

class SystemTests {
    companion object {
        @JvmStatic
        private fun systemValues(): Stream<Pair<Double, Double>> {
            return Stream.of(
                -4.2408 to -0.5,
                Double.NaN to 1.0,
                0.7250 to 2.0
            )
        }
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

        assertEquals(value.first, system.invoke(value.second), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with sin as mock and real ln`(value: Pair<Double, Double>) {
        Mockito.`when`(sin(value.second)).thenReturn(kotlin.math.sin(value.second))
        val system = System().apply { sin; cos; cot; sec; csc }

        assertEquals(value.first, system.invoke(value.second), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with ln as mock and real sin`(value: Pair<Double, Double>) {
        Mockito.`when`(ln(value.second)).thenReturn(kotlin.math.ln(value.second))
        val system = System().apply { ln; log }

        assertEquals(value.first, system.invoke(value.second), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with real sin and ln`(value: Pair<Double, Double>) {
        val system = System()
        assertEquals(value.first, system.invoke(value.second), 0.0001)
    }
}