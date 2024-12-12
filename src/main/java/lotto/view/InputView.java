package lotto.view;

import lotto.util.ErrorMessage;
import lotto.util.InputReader;
import lotto.util.OutputWriter;

import java.util.regex.Pattern;

public class InputView implements InputReader, OutputWriter {
    private static final Pattern PATTERN = Pattern.compile("^([a-z]+)+$");

    private void validate(String input) {
        if (!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }
}
