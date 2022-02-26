fun countingSort(arr: List<Int>): List<Int> {
    val max: Int = arr.maxOrNull()!!
    val min: Int = arr.minOrNull()!!
    val range = max - min + 1
    val count = IntArray(range)
    val output = IntArray(arr.size)
    for (element in arr) {
        count[element - min]++
    }

    for (i in 1 until count.size) {
        count[i] += count[i - 1]
    }

    for (i in arr.size - 1 downTo 0) {
        output[count[arr[i] - min] - 1] = arr[i]
        count[arr[i] - min]--
    }
    return output.toList()
}