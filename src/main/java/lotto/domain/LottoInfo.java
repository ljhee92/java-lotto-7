package lotto.domain;

import lotto.Lotto;

import java.util.List;

public record LottoInfo(List<Integer> numbers) {
    public static LottoInfo from(Lotto lotto) {
        return new LottoInfo(lotto.getNumbers());
    }
}
