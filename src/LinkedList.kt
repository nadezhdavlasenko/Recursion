// A simple Java program to introduce a linked list
class LinkedList(vararg elements: Int) {
    var head: Node? = null // head of list

    init {
        if (elements.isNotEmpty()) {
            head = Node(elements[0])
            var curr = head
            for (i in 1 until elements.size) {
                curr!!.next = Node(elements[i])
                curr = curr.next
            }
        }
    }

    fun printList() {
        var n = head
        while (n != null) {
            print(n.data.toString() + " ")
            n = n.next
        }
    }

    fun contentEquals(elements: LinkedList): Boolean {
        var curr = head
        var compareCurr = elements.head
        while (curr != null && compareCurr != null) {
            if (curr.data != compareCurr.data) return false
            curr = curr.next
            compareCurr = compareCurr.next
        }
        if (curr != null || compareCurr != null) return false
        return true
    }

    /* Linked list Node.  This inner class is made static so that
       main() can access it */
    class Node {
        val data: Int
        var next: Node?

        constructor(data: Int) {
            this.data = data
            next = null
        }
    }
}