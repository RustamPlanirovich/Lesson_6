package calculator

class Calculator(private var result: Int = 0) {
    fun get() = result
    fun add(other: Int): Unit {
        result += other
    }

    fun mul(other: Int): Unit {
        result *= other
    }

    fun sub(other: Int): Unit {
        result = -other
    }

    fun div(other: Int): Unit {
        assert(other != 0) {"Division by zero"}
        result /= other
    }
}


fun main() {
    val calc = Calculator(12)
    calc.add(12)
    calc.div(2)
    println(calc.get())
}