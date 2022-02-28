import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.math.tan

class TaylorSeriesTanTest {
    private val taylorSeriesTg = TaylorSeriesTan()

    @ParameterizedTest
    @ValueSource(doubles = [0.5, 1.2, 1.3])
    fun `Test taylor series tan`(number: Double) {
        Assertions.assertEquals(tan(number), taylorSeriesTg.calculate(number).toDouble(), 0.001)
    }

    @ParameterizedTest
    @ValueSource(doubles = [-PI / 2, PI / 2])
    fun `Test taylor series tan with NaN`(number: Double) {
        Assertions.assertEquals(Double.NaN, taylorSeriesTg.calculate(number).toDouble(), 0.001)
    }

    @ParameterizedTest
    @ValueSource(doubles = [-PI, 0.0, PI])
    fun `Test taylor series tan with zero`(number: Double) {
        Assertions.assertEquals(0.0, taylorSeriesTg.calculate(number).toDouble(), 0.001)
    }
}
