package lotto.domain;

import lotto.Lotto;
import lotto.util.ErrorMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    private WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto winningLotto, int bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private static void validateDuplicate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.isContain(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_AND_BONUS_NUMBER.getMessage());
        }
    }

    public Statistics checkWinnings(Lotto purchasedLotto) {
        List<Integer> duplicate = purchasedLotto.getNumbers().stream()
                .filter(number -> winningLotto.getNumbers().stream()
                        .anyMatch(Predicate.isEqual(number)))
                .toList();

        return Statistics.of(duplicate.size(), purchasedLotto.isContain(bonusNumber));
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "winningLotto=" + winningLotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
