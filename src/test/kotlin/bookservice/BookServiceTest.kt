package bookservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class BookServiceTest {

    @Test
    fun testLendIsCalled() {
        val bookService = mock(BookService::class.java)
        `when`(bookService.inStock(100)).thenReturn(true)

        val lendBookManager = LendBookManager(bookService)
        lendBookManager.checkout(100, 10)
        verify(bookService).lend(100, 10)
    }

    @Test
    fun testLendIsCalledFalse() {
        val bookService = mock(BookService::class.java)
        `when`(bookService.inStock(200)).thenReturn(false)
        val lendBookManager = LendBookManager(bookService)
        val throws = assertThrows<IllegalStateException>{
            lendBookManager.checkout(200, 10)
        }
        Assertions.assertEquals(throws.message, "Book is not available")
    }
}



