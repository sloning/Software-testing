package world

interface Engine {
    val name: String
    val thrust: Double
    val fuelType: FuelType

    fun activate()

    fun deactivate()

    fun isActive(): Boolean
}

enum class FuelType {
    IONIZED_INERT_GAS,
    LOX,
    METHANE,
    ELECTRICITY
}