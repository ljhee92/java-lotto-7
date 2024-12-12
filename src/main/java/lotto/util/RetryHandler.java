package lotto.util;

import java.util.function.Supplier;

public class RetryHandler {
    private RetryHandler() {}

    public static <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(supplier);
        }
    }

    public static void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            repeat(runnable);
        }
    }
}
