package math

import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.pow

open class Sin : MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x.isSpecial) x else generateSequence(x to 1) {
            it.first * -x.pow(2) / ((2 * it.second) * (2 * it.second + 1)) to it.second + 1
        }.takeWhile { it.first.absoluteValue > precision }.sumOf { it.first }.normalize(precision)
    }
}

open class Cos : MathFunction<Number> {
    val sin = Sin()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            return if (it.isSpecial) it else sin(it + PI / 2, precision = precision).normalize(precision)
        }
}

open class Cot : MathFunction<Number> {
    val cos = Cos()
    val sin = Sin()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            when {
                it.isSpecial -> it
                it == 0.0 -> Double.NaN
                else -> cos(it, precision = precision) / sin(it, precision = precision)
            }
        }
}

open class Sec : MathFunction<Number> {
    val cos = Cos()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            when {
                it.isSpecial -> it
                (it % (PI / 2) == 0.0) && (it % PI != 0.0) -> Double.POSITIVE_INFINITY
                else -> 1 / cos(it, precision = precision)
            }
        }
}

open class Csc : MathFunction<Number> {
    val sin = Sin()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            when {
                it.isSpecial -> it
                it == 0.0 -> Double.NaN
                else -> 1 / sin(it, precision = precision)
            }
        }
}
