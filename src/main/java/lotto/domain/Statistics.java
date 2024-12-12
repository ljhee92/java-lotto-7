package lotto.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Statistics {
    FIRST(6, false, BigDecimal.valueOf(2_000_000_000)),
    SECOND(5, true, BigDecimal.valueOf(30_000_000)),
    THIRD(5, false, BigDecimal.valueOf(1_500_000)),
    FOURTH(4, false, BigDecimal.valueOf(50_000)),
    FIFTH(3, false, BigDecimal.valueOf(5_000)),
    MISS(0, false, BigDecimal.ZERO);
    ;

    private final int matchCount;
    private final boolean isMatchWithBonusNumber;
    private final BigDecimal winnings;

    Statistics(int matchCount, boolean isMatchWithBonusNumber, BigDecimal winnings) {
        this.matchCount = matchCount;
        this.isMatchWithBonusNumber = isMatchWithBonusNumber;
        this.winnings = winnings;
    }

    public static Statistics of(int matchCount, boolean isMatchWithBonusNumber) {
        return Arrays.stream(Statistics.values())
                .filter(statistics -> statistics.matchCount == matchCount &&
                        statistics.isMatchWithBonusNumber == isMatchWithBonusNumber)
                .findAny().orElse(MISS);
    }
}
