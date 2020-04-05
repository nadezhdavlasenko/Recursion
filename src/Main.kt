import kotlin.test.assertEquals

fun main() {
    assertEquals(1, count_multiples(2, 6))
    assertEquals(2, count_multiples(2, 12))
    assertEquals(6, count_multiples(3, 11664))
}

fun count_multiples(a: Int, b: Int): Int {
    if (b % a != 0) {
        return 0
    }
    return 1 + count_multiples(a, b / a)
}