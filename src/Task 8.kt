import java.util.*
import kotlin.concurrent.thread

class PizzaBox(private val capacity: Int) {
    private val box: Queue<String> = LinkedList()

    @Synchronized
    fun put(pizza: String) {
        while (box.size >= capacity) {
            (this as Object).wait() // ждем, если коробка полная
        }
        box.add(pizza)
        println("🍕 Повар приготовил пиццу: $pizza (в коробке: ${box.size})")
        (this as java.lang.Object).notifyAll()
    }

    @Synchronized
    fun take(): String {
        while (box.isEmpty()) {
            (this as Object).wait() // ждем, если коробка пустая
        }
        val pizza = box.remove()
        println("🚚 Курьер забрал пиццу: $pizza (в коробке: ${box.size})")
        (this as java.lang.Object).notifyAll()
        return pizza
    }
}

fun main() {
    val pizzaBox = PizzaBox(5)

    // Повара
    repeat(2) { id ->
        thread {
            try {
                var pizzaId = 1
                while (!Thread.currentThread().isInterrupted) {
                    pizzaBox.put("Пицца #$pizzaId от повара $id")
                    pizzaId++
                    Thread.sleep(300) // время приготовления
                }
            }
            catch (_: InterruptedException) {
                println("Повар $id пошел домой")
            }
        }
    }

    // Курьеры
    repeat(3) { id ->
        thread {
            try {
                while (!Thread.currentThread().isInterrupted) {
                    pizzaBox.take()
                    Thread.sleep(500) // время доставки
                }
            }
            catch (_: InterruptedException) {
                println("Курьер $id пошел домой")
            }
        }
    }

    // Работаем 5 секунд
    Thread.sleep(10000)
    println("⏹ Пиццерия закрывается!")

    // Завершаем все потоки
    Thread.getAllStackTraces().keys
        .filter { it.name.contains("Thread") }
        .forEach { it.interrupt() }
}
