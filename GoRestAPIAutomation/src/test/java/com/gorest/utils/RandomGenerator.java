package com.gorest.utils;

import java.util.Random;

/**
 * Utility class to generate random strings consisting of alphanumeric, alphabetic, and numeric characters.
 * Provides methods to generate random strings with different constraints.
 */
public class RandomGenerator {

    // Constant strings containing valid characters for random generation
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC_STRING = "0123456789";
    private static final String NUMERIC_STRING_WITHOUT_ZERO = "123456789";
    
    private static final Random RANDOM = new Random();

    /**
     * Generates a random alphanumeric string.
     * 
     * @param count - Length of the random string to generate.
     * @return A random string containing alphabets and digits.
     */
    public static String randomAlphaNumeric(int count) {
        try {
            StringBuilder builder = new StringBuilder();
            while (count-- > 0) {
                int character = RANDOM.nextInt(ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            return builder.toString();
        } catch (Exception e) {
            System.err.println("Error generating random alphanumeric string: " + e.getMessage());
            return null;
        }
    }

    /**
     * Generates a random numeric string.
     * 
     * @param count - Length of the random numeric string to generate.
     * @return A random string containing digits (0-9).
     */
    public static String randomNumeric(int count) {
        try {
            StringBuilder builder = new StringBuilder();
            while (count-- > 0) {
                int character = RANDOM.nextInt(NUMERIC_STRING.length());
                builder.append(NUMERIC_STRING.charAt(character));
            }
            return builder.toString();
        } catch (Exception e) {
            System.err.println("Error generating random numeric string: " + e.getMessage());
            return null;
        }
    }

    /**
     * Generates a random numeric string that starts with a digit between 1 and 9.
     * 
     * @param count - Length of the random numeric string to generate.
     * @return A random string starting with a non-zero digit.
     */
    public static String randomNumericStartWith1(int count) {
        try {
            StringBuilder builder = new StringBuilder();
            while (count-- > 0) {
                int character = RANDOM.nextInt(NUMERIC_STRING_WITHOUT_ZERO.length());
                builder.append(NUMERIC_STRING_WITHOUT_ZERO.charAt(character));
            }
            return builder.toString();
        } catch (Exception e) {
            System.err.println("Error generating random numeric string (without 0): " + e.getMessage());
            return null;
        }
    }

    /**
     * Generates a random alphabetic string.
     * 
     * @param count - Length of the random alphabetic string to generate.
     * @return A random string containing only alphabets (A-Z).
     */
    public static String randomAlpha(int count) {
        try {
            StringBuilder builder = new StringBuilder();
            while (count-- > 0) {
                int character = RANDOM.nextInt(ALPHA_STRING.length());
                builder.append(ALPHA_STRING.charAt(character));
            }
            return builder.toString();
        } catch (Exception e) {
            System.err.println("Error generating random alphabetic string: " + e.getMessage());
            return null;
        }
    }
}
