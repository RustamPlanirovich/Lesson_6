package mokito

import mockito.MyClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.provider.Arguments
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mockito.*
import java.util.*

class TestingMockito {

    @Test
    fun test1() {
        val test: MyClass = mock(MyClass::class.java)
        `when`(test.getUniqeId()).thenReturn(43)
        assertEquals(43, test.getUniqeId())
    }

    @Test
    fun testTwoReturnValues() {
        val iter = mock(Iterator::class.java) as Iterator<String>
        `when`(iter.next()).thenReturn("Hello").thenReturn("world")
        val result = iter.next() + " " + iter.next()
        assertEquals("Hello world", result)
    }

    @Test
    fun testComparableReturnValue() {
        val iter = mock(Comparable::class.java) as Comparable<String>
        `when`(iter.compareTo("Hello")).thenReturn(2)
        `when`(iter.compareTo("world")).thenReturn(4)
        assertEquals(2, iter.compareTo("Hello"))
        assertEquals(4, iter.compareTo("world"))
    }

    @Test
    fun testThrows() {
        val props = mock(Properties::class.java)
        `when`(props["Linux"]).thenReturn("OK")
        `when`(props["Andddroid"]).thenThrow(IllegalArgumentException("Typo"))
        assertEquals("OK", props["Linux"])
        val throws = assertThrows<IllegalArgumentException> {
            props["Andddroid"]
        }
        assertEquals(throws.message, "Typo")
    }

    @Test
    fun testSpyWrong() {
        val list: List<String> = LinkedList()
        val spy: List<String> = spy(list)
//        `when`(spy[0]).thenReturn("hello")
        doReturn("hello").`when`(spy)[0]
        assertEquals(spy[0], "hello")
    }

    @Test
    fun testVerify() {
        val test = mock(MyClass::class.java)
        `when`(test.getUniqeId()).thenReturn(43)
        val inOrder = inOrder(test)
        test.testing(12)
        test.getUniqeId()
        test.getUniqeId()

        verify(test).testing(ArgumentMatchers.eq(12))
        verify(test, times(2)).getUniqeId()
        verify(test, atLeastOnce()).getUniqeId()
        verify(test, atLeast(1)).getUniqeId()
        verify(test, atMost(10)).getUniqeId()
        verify(test, never()).someMethod("never called")

        inOrder.verify(test).testing(12)
        inOrder.verify(test, times(2)).getUniqeId()
        verifyNoMoreInteractions(test)

    }

    //Captor
    @Captor
    lateinit var stringCapror: ArgumentCaptor<String>

    class MockitoHelper {
        fun <T> capture(captor: ArgumentCaptor<T>): T = captor.capture()
    }

    @Test
    fun shouldContainSting() {
        stringCapror = ArgumentCaptor.forClass(String::class.java)
        val helper = MockitoHelper()
        val list = LinkedList<String>()
        val spy = spy(list)
        spy.add("hello")
//        verify(spy).add(stringCapror.capture()) //IllegalStateException
        verify(spy).add(helper.capture(stringCapror))
        assertTrue(stringCapror.value == "hello")
    }
}