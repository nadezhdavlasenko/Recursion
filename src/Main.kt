import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun main() {
    assertEquals(1, count_multiples(2, 6))
    assertEquals(2, count_multiples(2, 12))
    assertEquals(6, count_multiples(3, 11664))

    assertEquals(13, maxval(intArrayOf(1, 9, -3, 7, 13, 2, 3)))

    assertEquals(3, count_occurrences(2, arrayOf(1, arrayOf(4, arrayOf(5, 2), 2), arrayOf(8, arrayOf(2, 9)))))
    assertEquals(1, count_occurrences(5, arrayOf(1, arrayOf(4, arrayOf(5, 2), 2), arrayOf(8, arrayOf(2, 9)))))

    assertTrue(arrayOf(1, 2, 8, 9, 10, 14, 15, 23).contentEquals(merge(arrayOf(1, 8, 9, 14, 15), arrayOf(2, 10, 23))))

    reverse(LinkedList(1, 2, 3, 4)).printList()
    println()
    LinkedList(1, 2, 3, 4).printList()
    assertTrue(LinkedList(4, 3, 2, 1).contentEquals(reverse(LinkedList(1, 2, 3, 4))))

    println()
    reverseRecursively(LinkedList(1, 2, 3, 4)).printList()
    println()
    LinkedList(1, 2, 3, 4).printList()
    assertTrue(LinkedList(4, 3, 2, 1).contentEquals(reverseRecursively(LinkedList(1, 2, 3, 4))))

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

fun merge(sorted1: Array<Int>, sorted2: Array<Int>): Array<Int> {
    val res = Array<Int>(sorted1.size + sorted2.size, { 0 })
    merge(sorted1, sorted2, 0, 0, res, 0)
    return res
}

private fun merge(
    sorted1: Array<Int>,
    sorted2: Array<Int>,
    ind1: Int,
    ind2: Int,
    merged: Array<Int>,
    indMerged: Int
) {
    if (ind1 < sorted1.size && ind2 < sorted2.size) {
        if (sorted1[ind1] <= sorted2[ind2]) {
            merged[indMerged] = sorted1[ind1]
            merge(sorted1, sorted2, ind1 + 1, ind2, merged, indMerged + 1)
        } else {
            merged[indMerged] = sorted2[ind2]
            merge(sorted1, sorted2, ind1, ind2 + 1, merged, indMerged + 1)
        }

    } else if (ind1 < sorted1.size) {
        merged[indMerged] = sorted1[ind1]
        merge(sorted1, sorted2, ind1 + 1, ind2, merged, indMerged + 1)
    } else if (ind2 < sorted2.size) {
        merged[indMerged] = sorted2[ind2]
        merge(sorted1, sorted2, ind1, ind2 + 1, merged, indMerged + 1)
    }
}

fun reverse(list: LinkedList): LinkedList {
    if (list.head == null || list.head!!.next == null) return list
    var i = list.head
    var j = i!!.next
    var k = j!!.next

    i.next = null
    while (k != null) {
        j!!.next = i
        i = j
        j = k
        k = k.next
    }
    j!!.next = i

    list.head = j

    return list
}

fun reverseRecursively(list: LinkedList): LinkedList {
    if (list.head == null || list.head!!.next == null) return list
    val i = list.head
    var j = i!!.next
    val k = j!!.next
    i.next = null
    j = reverse(list, j, k)
    j.next = list.head
    list.head = j

    return list
}

private fun reverse(list: LinkedList, j: LinkedList.Node, k: LinkedList.Node?): LinkedList.Node {
    if (k == null) return j
    j.next = list.head
    list.head = j
    return reverse(list, k, k.next)
}