package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    // ---- Addition ----
    @Test
    public void testAdd() {
        assertEquals(15.0, Main.add(5, 10));
        assertEquals(-15.0, Main.add(-5, -10));
        assertEquals(5.0, Main.add(-5, 10));
        assertEquals(0.0, Main.add(0, 0));
    }

    // ---- Subtraction ----
    @Test
    public void testSubtract() {
        assertEquals(5.0,  Main.subtract(10, 5));
        assertEquals(-5.0, Main.subtract(5, 10));
        assertEquals(0.0,  Main.subtract(7, 7));
        assertEquals(-20.0, Main.subtract(-10, 10));
    }

    // ---- Division ----
    @Test
    public void testDivide() {
        assertEquals(2.0,  Main.divide(10, 5));
        assertEquals(0.5,  Main.divide(1, 2));
        assertEquals(-3.0, Main.divide(-9, 3));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> Main.divide(10, 0));
    }
}
