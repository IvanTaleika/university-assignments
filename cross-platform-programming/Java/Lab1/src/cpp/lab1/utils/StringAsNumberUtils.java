package cpp.lab1.utils;

import java.text.DecimalFormat;

/**
 * Provides different utils for strings that represent numbers.
 */
public class StringAsNumberUtils {
    /**
     * Default number of decimal digits that are showed in a text field
     * after converting.
     */
    public static final int N_DECIMAL_DIGITS_DEFAULT = 2;

    private int nDecimalDigits;

    /**
     * Default constructor.
     */
    public StringAsNumberUtils() {
        this.nDecimalDigits = N_DECIMAL_DIGITS_DEFAULT;
    }

    /**
     * Returns {@link DecimalFormat} object maximum with fraction digits
     * number that coincides with fraction digits number in
     * {@code stringAsNumber} if this number is higher that
     * {@code nDecimalDigits}, otherwise maximum fraction digits number is
     * equal to {@code nDecimalDigits}.
     *
     * @param stringAsNumber String that represents number
     * @return DecimalFormat object
     */
    public DecimalFormat getTextDecimalFormat(final String stringAsNumber) {
        int numberOfDecimalDigit = stringAsNumber.indexOf('.') == -1
                ? 0
                : stringAsNumber.length() - stringAsNumber.indexOf('.') - 1;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(
                numberOfDecimalDigit > nDecimalDigits
                        ? numberOfDecimalDigit
                        : nDecimalDigits);
        return decimalFormat;
    }

}
