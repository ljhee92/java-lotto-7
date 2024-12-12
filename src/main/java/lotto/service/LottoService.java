package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.LottoInfo;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.StatisticsInfo;
import lotto.domain.WinningLotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private List<Lotto> lottos;
    private Map<Statistics, Integer> result;

    public void issueLottos(int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = Lotto.of(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
        }
        this.lottos = lottos;
    }

    public List<LottoInfo> getLottoInfos() {
        List<LottoInfo> lottoInfos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoInfos.add(LottoInfo.from(lotto));
        }
        return lottoInfos;
    }

    public void draw(WinningLotto winningLotto) {
        Map<Statistics, Integer> result = new EnumMap<>(Statistics.class);
        Arrays.stream(Statistics.values()).forEach(statistics -> {
            result.put(statistics, 0);
        });
        for (Lotto lotto : lottos) {
            Statistics statistics = winningLotto.checkWinnings(lotto);
            result.put(statistics, result.get(statistics) + 1);
        }
        this.result = result;
    }

    public StatisticsInfo getStatisticsInfo() {
        return StatisticsInfo.of(result);
    }

    public double getProfit(Money purchaseAmount) {
        BigDecimal rateOfReturn = BigDecimal.ZERO;
        for (Statistics statistics : Statistics.values()) {
            rateOfReturn = rateOfReturn.add(
                    statistics.getWinnings().multiply(BigDecimal.valueOf(result.get(statistics)))
            );
        }
        return rateOfReturn.divide(purchaseAmount.getAmount(), 3, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100)).doubleValue();
    }
}
