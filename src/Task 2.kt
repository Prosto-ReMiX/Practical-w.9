fun main() {
    val thread1 = Thread { sayHello("Thread 1") }
    val thread2 = Thread { sayHello("Thread 2") }

    thread1.start()
    thread2.start()
}
