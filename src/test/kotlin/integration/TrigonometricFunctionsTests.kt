package integration

import math.*
import org.mockito.Mockito
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class TrigonometricFunctionsTests {
    val cos: Cos = Mockito.mock(Cos::class.java)
    val sin: Sin = Mockito.mock(Sin::class.java)

    @Test
    fun `cos integration with sin test`() {
        val cos = Cos().apply { sin }
        Mockito.`when`(sin(0.0)).thenReturn(1.0)
        assertEquals(0.0, cos(-PI / 2), 0.0001)
    }

    @Test
    fun `cot integration with cos and sin test`() {
        val cot = Cot().apply { cos; sin }
        Mockito.`when`(cos(PI / 2)).thenReturn(0.0)
        Mockito.`when`(sin(PI / 2)).thenReturn(1.0)
        assertEquals(0.0, cot(PI / 2), 0.0001)
    }

    @Test
    fun `sec integration with cos test`() {
        val sec = Sec().apply { cos }
        Mockito.`when`(cos(PI)).thenReturn(-1.0)
        assertEquals(-1.0, sec(PI), 0.0001)
    }
}