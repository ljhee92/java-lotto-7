package lotto.util;

public enum ErrorMessage {
    INVALID_INPUT_EXCEPTION("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_LOTTO_NUMBER_SIZE("로또 번호는 6개여야 합니다.")
    ;

    private static final String prefix = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix + message;
    }
}
