package cpp.lab1.tests;

import cpp.lab1.utils.StringAsNumberUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringAsNumberUtilsTest {
    int nDecimalDigits;
    StringAsNumberUtils stringAsNumberUtils;
    String checkString;

    @BeforeAll
    void initAll() {
        nDecimalDigits = 0;
        stringAsNumberUtils = new StringAsNumberUtils();
        checkString = "3.";
    }

    @AfterEach
    void tearDown() {
        nDecimalDigits++;
        checkString += '1';
    }

    @Test
    @RepeatedTest(15)
    void getTextDecimalFormat() {
        if (nDecimalDigits < StringAsNumberUtils.N_DECIMAL_DIGITS_DEFAULT) {
            assertEquals(StringAsNumberUtils.N_DECIMAL_DIGITS_DEFAULT,
                    stringAsNumberUtils.getTextDecimalFormat(checkString)
                            .getMaximumFractionDigits());
        } else {
            assertEquals(nDecimalDigits,
                    stringAsNumberUtils.getTextDecimalFormat(checkString)
                            .getMaximumFractionDigits());
        }
    }
}