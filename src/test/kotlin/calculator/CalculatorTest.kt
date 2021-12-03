package calculator

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.lang.AssertionError
import java.util.stream.Stream

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {
    init {
        println("init")
    }

    companion object Initializer {
        @BeforeAll
        @JvmStatic
        fun prepare() {
            println("prepare")
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            println("tearDown")
        }
    }


    @BeforeEach
    fun prepareTest() {
        println("prepareTest")
    }


    @Test
    @DisplayName("Calculator Add Test Func")
    fun calcAdd() {
        println("calcAdd")
        val calculator = Calculator(15)
        calculator.add(2)
        assertEquals(
            17,
            calculator.get(),
            "15 + 2 should give 17"
        )
    }

    @Test
    fun calcMul() {
        println("calcMul")
        val calculator = Calculator(15)
        calculator.mul(2)
        assertEquals(
            30,
            calculator.get(),
            "15 * 2 should give 30"
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1, 1",
        "10,5,15",
        "1,100,101"
    )
    fun paraAdd(f: Int, s: Int, r: Int) {
        println("paraAdd")
        val calculator = Calculator(f)
        calculator.add(s)
        assertEquals(
            r,
            calculator.get(),
            "$f + $s should give $r"
        )
    }

    private fun data(): Stream<Arguments> = Stream.of(
        Arguments.of(0, 0, 0),
        Arguments.of(0, 3, 0),
        Arguments.of(12, 6, 72),
        Arguments.of(3, 6, 18),
    )

    @ParameterizedTest
    @MethodSource("data")
    fun paraMul(f: Int, s: Int, r: Int) {
        println("paraMul")
        val calculator = Calculator(f)
        calculator.mul(s)
        assertEquals(
            r,
            calculator.get(),
            "$f + $s should give $r"
        )
    }

    @Test
    fun divisionByZeroThrowException() {
        val calculator = Calculator(10)
        val exception = assertThrows<AssertionError> {
            calculator.div(0)
        }
        assertEquals("Division by zero", exception.message, "Should be 'Division by zero'")
    }

    @Test
    @Disabled
    fun combineAddAndMul() {
        val calculator = Calculator(15)
        assertAll(
            "Проверка сложения и умножения",
            {
                calculator.add(15)
                assertEquals(
                    30,
                    calculator.get()
                )
            },
            {
                calculator.mul(2)
                assertEquals(
                    60,
                    calculator.get()
                )
            }
        )
    }


}