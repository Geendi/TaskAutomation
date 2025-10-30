package utils;

public class TestDataGenerator {

    /**
     * Generates a unique email address using a timestamp.
     * This ensures the email is unique for every registration attempt.
     */
    public static String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp + "@example.com";
    }
}