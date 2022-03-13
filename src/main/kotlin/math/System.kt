package math

import kotlin.math.pow

class System : MathFunction<Number> {
    val sin = Sin()
    val cos = Cos()
    val cot = Cot()
    val sec = Sec()
    val csc = Csc()
    val ln = Ln()
    val log = Log()

    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x <= 0)
            (((((((cot(x, precision = precision).pow(3)) / cot(x, precision = precision)) + ((csc(
                x,
                precision = precision
            ) - sin(x, precision = precision)) - sin(x, precision = precision))) - (sec(
                x,
                precision = precision
            ).pow(3))).pow(2)) / sin(x, precision = precision)) + (csc(x, precision = precision) - ((sin(
                x,
                precision = precision
            ) / sin(x, precision = precision)).pow(3))))
        else
            (((((log(x, 5, precision = precision) / log(x, 2, precision = precision)) * log(
                x,
                2,
                precision = precision
            )) - log(x, 3, precision = precision)) + (ln(x) / log(x, 3, precision = precision))).pow(3))
    }
}