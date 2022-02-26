package world

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShipTest {
    private fun createEngine(): Engine = PhotonEngine("Test engine", 2.5)

    private fun createShip(): Ship = Ship("Test ship", createEngine(), 4)

    private fun createHuman(): Human = Human("Test Human", Human.Feeling.HAPPY)

    @Test
    fun `Test add new crew members to ship`() {
        val ship = createShip()
        val human = createHuman()
        ship.addCrewMate(human)
        Assertions.assertTrue(ship.crew.contains(human))
    }

    @Test
    fun `Test remove of members`() {
        val ship = createShip()
        val human = createHuman()
        ship.addCrewMate(human)
        ship.removeCrewMate(human)
        Assertions.assertFalse(ship.crew.contains(human))
    }

    @Test
    fun `Test limit of crew`() {
        val ship = createShip()
        val human = createHuman()
        ship.addCrewMate(human)
        ship.addCrewMate(human)
        ship.addCrewMate(human)
        ship.addCrewMate(human)
        Assertions.assertThrows(MaximumNumberOfCrewException::class.java) {
            ship.addCrewMate(human)
        }
    }

    @Test
    fun `Test remove of non-existent member`() {
        val ship = createShip()
        val human = createHuman()
        Assertions.assertThrows(NoSuchCrewMateException::class.java) {
            ship.removeCrewMate(human)
        }
    }

    @Test
    fun `Test engine activation`() {
        val ship = createShip()
        ship.startEngine()
        Assertions.assertTrue(ship.engine.isActive())
    }

    @Test
    fun `Test engine deactivation`() {
        val ship = createShip()
        ship.startEngine()
        ship.stopEngine()
        Assertions.assertFalse(ship.engine.isActive())
    }

    @Test
    fun `Test ship equality`() {
        val firstShip = createShip()
        val secondShip = createShip()
        Assertions.assertTrue(firstShip == secondShip)
    }

    @Test
    fun `Test ship inequality`() {
        val firstShip = createShip()
        val secondShip = Ship("Not test ship", createEngine(), 1)
        Assertions.assertFalse(firstShip == secondShip)
    }

    @Test
    fun `Test toString`() {
        val ship = createShip()
        Assertions.assertEquals(ship.toString(), "Space ship: ${ship.name} has engine: ${ship.engine.name} and " +
                "can have maximum number of crew of ${ship.maximumNumberOfCrew}")
    }

    @Test
    fun `Test hashCode`() {
        val firstShip = createShip()
        val secondShip = createShip()
        Assertions.assertEquals(firstShip.hashCode(), secondShip.hashCode())
    }
}