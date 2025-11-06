package bmi.bmicalculatormysql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void calculateBMI() {
        HelloController hc = new HelloController();
        assertEquals(24.22, hc.calculateBMI(70, 170), 0.0001);
    }
}