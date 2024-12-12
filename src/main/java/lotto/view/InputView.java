package lotto.view;

import lotto.util.ErrorMessage;
import lotto.util.InputReader;
import lotto.util.OutputWriter;
import lotto.util.Parser;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class InputView implements InputReader, OutputWriter {
    private static final Pattern PATTERN = Pattern.compile("^[0-9]+$");

    public BigDecimal requestPurchaseAmount() {
        displayMessageByLine("구입금액을 입력해 주세요.");
        String input = inputMessage();
        validatePurchaseAmount(input);
        return BigDecimal.valueOf(Parser.parseToInt(input));
    }

    private void validatePurchaseAmount(String input) {
        if (!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }
}
