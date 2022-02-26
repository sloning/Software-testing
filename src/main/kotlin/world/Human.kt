package world

data class Human(
    val name: String,
    val feeling: Feeling,
    private val knowledge: MutableList<String> = ArrayList()
) {
    fun addKnowledge(newKnowledge: String) {
        knowledge.add(newKnowledge)
    }

    fun getKnowledge(): List<String> = knowledge

    enum class Feeling { UNCOMFORTABLE, HAPPY }
}