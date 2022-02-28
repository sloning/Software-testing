fun countingSort(arr: List<Int>): List<Int> {
    if (arr.isEmpty())
        return arr
    val array = arr.toIntArray()
    val min = array.minOrNull()!!
    val max = array.maxOrNull()!!
    val count = IntArray(max - min + 1)
    for (number in array)
        count[number - min]++
    var z = 0
    for (i in min..max)
        while (count[i - min] > 0) {
            array[z++] = i
            count[i - min]--
        }
    return array.toList()
}