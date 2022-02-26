package world

data class PhotonEngine(
    override val name: String,
    override val thrust: Double,
    override val fuelType: FuelType = FuelType.ELECTRICITY,
    private var isActive: Boolean = false
) : Engine {
    override fun activate() {
        isActive = true
    }

    override fun deactivate() {
        isActive = false
    }

    override fun isActive() = isActive

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotonEngine

        if (name != other.name) return false
        if (thrust != other.thrust) return false
        if (fuelType != other.fuelType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + thrust.hashCode()
        result = 31 * result + fuelType.hashCode()
        return result
    }
}