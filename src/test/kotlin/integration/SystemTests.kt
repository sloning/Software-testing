package integration

import math.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.math.sin
import kotlin.test.assertEquals

class SystemTests {
    companion object {
        @JvmStatic
        private fun systemValues(): Stream<Pair<Double, Double>> {
            return Stream.of((-5 * PI / 6) to -28.05755, 1.0 to 1.32597)
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
        Mockito.`when`(sin(value.first)).thenReturn(kotlin.math.sin(value.first))
        Mockito.`when`(ln(value.first)).thenReturn(kotlin.math.ln(value.first))
        val system = System().apply { sin; cos; cot; sec; csc; ln; log }

        assertEquals(value.second, system.invoke(value.first), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with sin as mock and real ln`(value: Pair<Double, Double>) {
        Mockito.`when`(sin(value.first)).thenReturn(kotlin.math.sin(value.first))
        val system = System().apply { sin; cos; cot; sec; csc }

        assertEquals(value.second, system.invoke(value.first), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with ln as mock and real sin`(value: Pair<Double, Double>) {
        Mockito.`when`(ln(value.first)).thenReturn(kotlin.math.ln(value.first))
        val system = System().apply { ln; log }

        assertEquals(value.second, system.invoke(value.first), 0.0001)
    }

    @ParameterizedTest
    @MethodSource("systemValues")
    fun `math system integration with real sin and ln`(value: Pair<Double, Double>) {
        val system = System()
        assertEquals(value.second, system.invoke(value.first), 0.0001)
    }
}