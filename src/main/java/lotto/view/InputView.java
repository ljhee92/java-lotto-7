package lotto.view;

import lotto.util.ErrorMessage;
import lotto.util.InputReader;
import lotto.util.OutputWriter;
import lotto.util.Parser;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

public class InputView implements InputReader, OutputWriter {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
    private static final Pattern WINNING_LOTTO = Pattern.compile("^([1-9]+)(,([1-9]+))+$");

    public BigDecimal requestPurchaseAmount() {
        displayMessageByLine("구입금액을 입력해 주세요.");
        String input = inputMessage();
        validateNumberPattern(input);
        return BigDecimal.valueOf(Parser.parseToInt(input));
    }

    private void validateNumberPattern(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }

    public List<Integer> requestWinningLotto() {
        displayNewLine();
        displayMessageByLine("당첨 번호를 입력해 주세요.");
        String input = inputMessage();
        validateWinningLotto(input);
        return Parser.parseToList(input).stream().mapToInt(Integer::valueOf).boxed().toList();
    }

    private void validateWinningLotto(String input) {
        if (!WINNING_LOTTO.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }

    public int requestBonusNumber() {
        displayNewLine();
        displayMessageByLine("보너스 번호를 입력해 주세요.");
        String input = inputMessage();
        validateNumberPattern(input);
        return Parser.parseToInt(input);
    }
}
