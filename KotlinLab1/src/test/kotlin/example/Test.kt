package example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CalculatorTest {
    @Test
    fun testAdd() {
        assertEquals(4, add(2, 2))
    }
}