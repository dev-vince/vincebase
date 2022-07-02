package dev.vince.example.api.utils;

import java.util.Random;

public class MathUtil {
    public int getRandInt(final int min, final int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
