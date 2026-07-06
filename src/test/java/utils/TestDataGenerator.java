package utils;

import java.util.Random;

public class TestDataGenerator {
    private static final Random RANDOM = new Random();

    public static String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        int randomNum = RANDOM.nextInt(10000);
        return "diplom_test_" + timestamp + "_" + randomNum + "@example.com";
    }

    public static String generateName() {
        return "TestUser_" + System.currentTimeMillis();
    }

    public static String generateValidPassword() {
        return "Password123";
    }

    public static String generateShortPassword() {
        return "12345";
    }
}