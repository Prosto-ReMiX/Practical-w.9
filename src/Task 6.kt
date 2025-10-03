
fun ArrayList<Int>.producer() {
    synchronized(this) {
        while (size >= 3) {
            (this as Object).wait()
        }
        add(0)
        print("Добавлен 1 элемент; Товары: ")
        forEach { print("[$it]") }
        println()
        (this as Object).notify()
    }
}

fun ArrayList<Int>.consumer() {
    synchronized(this) {
        while (isEmpty()) {
            (this as Object).wait()
        }
        removeLast()
        print("Куплен 1 элемент; Товары: ")
        forEach { print("[$it]") }
        println()
        (this as Object).notify()
    }
}


fun main() {
    val store = arrayListOf<Int>()

    val thread = Thread{
        for (i in 1..50) {
            store.producer()
        }
    }
    val thread1 = Thread {
        for (i in 1..50) {
            store.consumer()
        }
    }

    thread.start()
    thread1.start()
}