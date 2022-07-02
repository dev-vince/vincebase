package dev.vince.example.api.utils;

import java.util.Random;

public final class MathUtil {
    public static int getRandInt(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
