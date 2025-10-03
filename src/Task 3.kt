fun main() {
    val threads = Array(5) { i ->
        Thread { sayHello("Thread $i") }
    }
    threads.forEach { it.start() }
}

