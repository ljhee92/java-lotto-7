package lotto;

import lotto.util.ErrorMessage;

import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_SIZE.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
