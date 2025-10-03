import kotlin.concurrent.thread

fun main() {
    val thread1 = thread { sayHello("Thread 1") }
    val thread2 = thread { sayHello("Thread 2") }

    thread1.start()
    thread2.start()
}
