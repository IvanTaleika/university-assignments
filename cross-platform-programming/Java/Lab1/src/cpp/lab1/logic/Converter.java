package cpp.lab1.logic;

/**
 * Utility-only class that provides methods to convert values.
 */
public final class Converter {
    /**
     * Convert constant for metres to inches converting formula.
     */
    public static final double INCH_TO_METRE = 0.0254;
    /**
     * Convert constant for metres to inches converting formula.
     */
    public static final double METRE_TO_INCH = 39.37;


    /**
     * Converts metres to inches.
     *
     * @param metre a length of value in metres
     * @return      a length of value in inches
     */
    public static double metreToInch(final double metre) {
        return metre * METRE_TO_INCH;
    }

    /**
     * Converts inches to metres.
     *
     * @param inch a length of value in inches
     * @return     a length of value in metres
     */
    public static double inchToMetre(final double inch) {
        return inch * INCH_TO_METRE;
    }

    /**
     * Hide default parameter-less constructor.
     */
    private Converter() {
    }
}
