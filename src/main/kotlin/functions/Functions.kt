package functions

import java.lang.StringBuilder

//расширение Int isOdd() isEven()
//fun Int.isOdd(): Boolean = this % 2 == 1
//fun Int.isEven(): Boolean = this % 2 == 0
val isEven: Int.() -> Boolean = { this % 2 == 0 }
val isOdd: Int.() -> Boolean = { this % 2 == 1 }

class LazyClass(val initializer: () -> Int) {

    val initial: Int by lazy { initializer() }
}

class Counter {

    var numberOfGets = 0

    var number: Int = 0
        get() = numberOfGets++
}

fun Collection<String>.formatString(
    prefix: String = "[",
    suffix: String = "]",
    delim: String = ",",
    processor: (String) -> String = { it }
): String {
    val result = StringBuilder()
    result.append(prefix)
    for ((index, element) in this.withIndex()) {
        if (index != 0)
            result.append(delim)
        result.append(processor(element))

    }
    result.append(suffix)
    return result.toString()
}

fun main() {

    println(listOf("abc", "def", "jhk").formatString("{", "}", ",") { "" + it.length })
//    println(12.isEven())
//    println(15.isOdd())

//    val init: () -> Int = {
//        println("init")
//        150
//    }

//    val lazyClass = LazyClass(init)
//    lazyClass.initial
//    lazyClass.initial
//    val claz = Counter()
//    claz.number
//    claz.number
//    println(claz.numberOfGets)
}