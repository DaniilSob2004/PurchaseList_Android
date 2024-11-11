package com.example.purchaselist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 0;
    private static final int MAX_INT = 100_000_000;


    public static String getFormattedDate(int seconds) {
        Date dateObject = new Date((long) seconds * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(dateObject);
    }

    public static int getCurrentDateBySecond() {
        return (int)(System.currentTimeMillis() / 1000);
    }


    public static int getRandomInteger() {
        return getRandomInteger(MIN_INT, MAX_INT);
    }

    public static int getRandomInteger(int max) {
        return getRandomInteger(MIN_INT, max);
    }

    public static int getRandomInteger(final int min, final int max) {
        return RAND.nextInt((max - min) + 1) + min;
    }
}
