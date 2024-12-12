package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.LottoInfo;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.StatisticsInfo;
import lotto.domain.WinningLotto;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money purchaseAmount = RetryHandler.repeat(this::getPurchaseAmount);
        int ticketCount = purchaseAmount.getTicketCount();

        List<Lotto> lottos = new ArrayList<>();
        List<LottoInfo> lottoInfos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = Lotto.of(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
            lottoInfos.add(LottoInfo.from(lotto));
        }
        outputView.displayPurchaseResult(lottoInfos, ticketCount);

        Lotto inputWinningLotto = RetryHandler.repeat(this::getInputWinningLotto);
        WinningLotto winningLotto = RetryHandler.repeat(() -> getWinningLotto(inputWinningLotto));

        Map<Statistics, Integer> result = new EnumMap<>(Statistics.class);
        Arrays.stream(Statistics.values()).forEach(statistics -> {
            result.put(statistics, 0);
        });
        for (Lotto lotto : lottos) {
            Statistics statistics = winningLotto.checkWinnings(lotto);
            result.put(statistics, result.get(statistics) + 1);
        }
        outputView.displayStatistics(StatisticsInfo.of(result));
    }

    private Money getPurchaseAmount() {
        return Money.of(inputView.requestPurchaseAmount());
    }

    private WinningLotto getWinningLotto(Lotto inputWinningLotto) {
        return WinningLotto.of(inputWinningLotto, inputView.requestBonusNumber());
    }

    private Lotto getInputWinningLotto() {
        return Lotto.of(inputView.requestWinningLotto());
    }
}
