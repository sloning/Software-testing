package world

data class Ship(
    var name: String,
    var engine: Engine,
    val maximumNumberOfCrew: Int,
    val crew: MutableList<Human> = ArrayList()
) {
    fun addCrewMate(newCrewMate: Human) {
        if (crew.size == maximumNumberOfCrew) {
            throw MaximumNumberOfCrewException()
        }
        crew.add(newCrewMate)
    }

    fun removeCrewMate(crewMate: Human) {
        if (!crew.remove(crewMate)) {
            throw NoSuchCrewMateException()
        }
    }

    fun startEngine() {
        engine.activate()
    }

    fun stopEngine() {
        engine.deactivate()
    }

    override fun toString(): String {
        return "Space ship: $name has engine: ${engine.name} and can have maximum number of crew of $maximumNumberOfCrew"
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + engine.hashCode()
        result = 31 * result + maximumNumberOfCrew
        result = 31 * result + crew.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ship

        if (name != other.name) return false
        if (engine != other.engine) return false
        if (maximumNumberOfCrew != other.maximumNumberOfCrew) return false

        return true
    }
}