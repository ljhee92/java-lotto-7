package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private Parser() {}

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }

    public static List<String> parseToList(String input) {
        return Arrays.stream(input.split(",")).toList();
    }
}
