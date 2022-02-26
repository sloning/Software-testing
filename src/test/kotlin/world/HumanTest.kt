package world

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HumanTest {
    private fun createHuman(): Human = Human("Test Human", Human.Feeling.HAPPY)

    @Test
    fun `Test add new knowledge`() {
        val human = createHuman()
        val knowledge = "Что-то там про молекулы и атомы"
        val knowledgeList = arrayListOf(knowledge)
        human.addKnowledge(knowledge)
        Assertions.assertEquals(human.getKnowledge(), knowledgeList)
    }
}