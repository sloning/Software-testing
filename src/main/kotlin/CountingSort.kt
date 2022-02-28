fun countingSort(arr: List<Int>): List<Int> {
    val max: Int = arr.maxOrNull()!!
    val min: Int = arr.minOrNull()!!
    val range = max - min + 1
    val count = HashMap<Int, Int>()
    val output = IntArray(arr.size)
    for (element in arr) {
        count[element] = 1
    }

    for (i in 2 until count.size) {
        count[i] = count[i - 1]?.let { count[i]?.plus(it) }!!
    }

    for (i in arr.size - 1 downTo 0) {
        output[count[arr[i] - min]?.minus(1)!!] = arr[i]
        count[arr[i] - min]?.minus(1)
    }
    return output.toList()
}