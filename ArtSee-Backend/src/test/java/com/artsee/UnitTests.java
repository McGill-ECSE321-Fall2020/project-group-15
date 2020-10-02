package com.artsee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    @Test
    public void testAdd() {
        assertEquals(2, Calculator.add(1, 1), "1 + 1 must be 2");
    }
    
}
