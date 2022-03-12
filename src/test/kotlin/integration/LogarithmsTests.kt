package integration

import math.Ln
import math.Log
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class LogarithmsTests {
    val ln: Ln = Mockito.mock(Ln::class.java)

    @Test
    fun `log integration with ln test`() {
        val log = Log().apply { ln }
        Mockito.`when`(ln(0)).thenReturn(Double.NaN)
        Mockito.`when`(ln(10)).thenReturn(2.3025)
        assertEquals(Double.NaN, log(0, 10))
    }
}