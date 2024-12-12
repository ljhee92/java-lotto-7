package lotto.util;

public interface OutputWriter {
    default void displayMessage(String message) {
        System.out.print(message);
    }

    default void displayMessageByLine(String message) {
        System.out.println(message);
    }

    default void displayNewLine() {
        System.out.println();
    }

    default void displayFormat(String format, Object ... args) {
        System.out.println(String.format(format, args));
    }
}
