import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class TaylorSeriesTan {
    fun calculateFactorial(n: Int): BigInteger {
        var result = BigInteger(1.toString())
        for (i in 1..n) {
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        return result
    }

    fun calculate(x: Double): Number {
        return if (x % (Math.PI / 2) == 0.0 && x % Math.PI != 0.0) Double.NaN
            else calculateSin(x).divide(calculateCos(x), 5, RoundingMode.HALF_UP)
    }

    fun calculateSin(x: Double): BigDecimal {
        var sin = BigDecimal(0)
        for (n in 0..899) {
            sin = sin.add(
                BigDecimal(-1).pow(n).multiply(BigDecimal(x).pow(2 * n + 1))
                    .divide(BigDecimal(calculateFactorial(2 * n + 1)), 5, RoundingMode.HALF_UP)
            )
        }
        return sin
    }

    fun calculateCos(x: Double): BigDecimal {
        var cos = BigDecimal(0)
        for (n in 0..899) {
            cos = cos.add(
                BigDecimal(-1).pow(n).multiply(BigDecimal(x).pow(2 * n))
                    .divide(BigDecimal(calculateFactorial(2 * n)), 5, RoundingMode.HALF_UP)
            )
        }
        return cos
    }
}