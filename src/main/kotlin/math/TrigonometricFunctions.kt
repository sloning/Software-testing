package math

import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.pow

class Sin: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x.isSpecial) x else generateSequence(x to 1) {
            it.first * -x.pow(2) / ((2 * it.second) * (2 * it.second + 1)) to it.second + 1
        }.takeWhile { it.first.absoluteValue > precision }.sumOf { it.first }
    }
}

class Cos: MathFunction<Number> {
    val sin = Sin()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            return if (it.isSpecial) it else sin(2 * it, precision = precision) / (2 * sin(it, precision = precision))
        }
}

class Cot: MathFunction<Number> {
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

class Sec: MathFunction<Number> {
    val cos = Cos()

    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            when {
                it.isSpecial -> it
                (it % (PI / 2) == 0.0) && (it % PI != 0.0) -> Double.NaN
                else -> 1 / cos(it, precision = precision)
            }
        }
}

class Csc: MathFunction<Number> {
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
