import kotlin.test.assertEquals

fun main() {
    assertEquals(1, count_multiples(2, 6))
    assertEquals(2, count_multiples(2, 12))
    assertEquals(6, count_multiples(3, 11664))

    assertEquals(13, maxval(intArrayOf(1, 9, -3, 7, 13, 2, 3)))

    assertEquals(3, count_occurrences(2, arrayOf(1, arrayOf(4, arrayOf(5, 2), 2), arrayOf(8, arrayOf(2, 9)))))
    assertEquals(1, count_occurrences(5, arrayOf(1, arrayOf(4, arrayOf(5, 2), 2), arrayOf(8, arrayOf(2, 9)))))
}

fun count_multiples(a: Int, b: Int): Int {
    if (b % a != 0) {
        return 0
    }
    return 1 + count_multiples(a, b / a)
}

fun maxval(array: IntArray): Int {
    return maxval(array, 0, array[0])
}

private fun maxval(array: IntArray, index: Int, localMax: Int): Int {
    if (index == array.size) return localMax
    return if (array[index] > localMax) maxval(array, index + 1, array[index])
    else maxval(array, index + 1, localMax)
}

fun count_occurrences(item: Int, array: Array<Any>): Int {
    var count = 0
    for (i in array.indices) {
        if (array[i] is Array<*>) {
            count += count_occurrences(item, array[i] as Array<Any>)
        } else if (array[i] == item) count += 1
    }
    return count
}