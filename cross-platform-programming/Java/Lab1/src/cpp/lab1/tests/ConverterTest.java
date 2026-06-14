package cpp.lab1.tests;

import cpp.lab1.logic.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {
    double metre;
    double inch;
    final Random random = new Random();


    @BeforeEach
    void setUp() {
        metre = random.nextDouble();
        inch = random.nextDouble();
    }

    @Test
    @RepeatedTest(10)
    void metreToInch() {
        assertEquals(metre * Converter.METRE_TO_INCH,
                Converter.metreToInch(metre));
    }

    @Test
    @RepeatedTest(10)
    void inchToMetre() {
        assertEquals(inch * Converter.INCH_TO_METRE,
                Converter.inchToMetre(inch));
    }
}