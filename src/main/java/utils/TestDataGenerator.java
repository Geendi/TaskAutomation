package utils;

public class TestDataGenerator {

    /**
     * Generates a unique email address using a timestamp.
     * This ensures the email is unique for every registration attempt.
     * @return A unique email string (e.g., user_1700000000000@automation.test)
     */
    public static String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp + "@example.com";
    }

    public static String generateIncorrectEmail(){
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp;
    }
}