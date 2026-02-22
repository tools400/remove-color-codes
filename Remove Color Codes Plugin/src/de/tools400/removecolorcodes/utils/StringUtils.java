package de.tools400.removecolorcodes.utils;

public final class StringUtils {

    private static final String DOUBLE_QUOTES = "\"";

    private StringUtils() {
    }

    /**
     * Returns <code>true</code> if a given string is <code>Null</code> or
     * empty, otherwise returns <code>false</code>.
     * 
     * @param aValue - String that is checked for a null or empty value.
     * @return <code>true</code> if the string is null or empty, otherwise
     *         <code>false</code>.
     */
    public static boolean isNullOrEmpty(String aValue) {

        if (aValue == null || aValue.length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * Encloses a given string value in double quotes.
     * 
     * @param value - string value that is enclosed in double quotes.
     * @return Quoted value.
     */
    public static String doubleQuotes(String value) {

        String quotesValue = DOUBLE_QUOTES + value + DOUBLE_QUOTES;

        return quotesValue;
    }

    /**
     * Removes leading spaces from a specified input string.
     * 
     * @param aValue - Value that is trimmed to the left side.
     * @return Value without leading spaces.
     */
    public static String trimL(String aValue) {
        return aValue.replaceAll("^\\s+", "");
    }

    /**
     * Removes trailing spaces from a specified input string.
     * 
     * @param aValue - Value that is trimmed to the right side.
     * @return Value without trailing spaces
     */
    public static String trimR(String aValue) {
        return aValue.replaceAll("\\s+$", "");
    }
}