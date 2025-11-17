package io.github.thepoultryman.arrp_but_different.util;

public record PackVersionRange(int min, int max) {
    public static PackVersionRange unbounded(int min) {
        return new PackVersionRange(min, Integer.MAX_VALUE);
    }
}
