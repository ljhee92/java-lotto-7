package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.LottoInfo;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

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

        List<LottoInfo> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = Lotto.of(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(LottoInfo.from(lotto));
        }
        outputView.displayPurchaseResult(lottos, ticketCount);

        Lotto inputWinningLotto = RetryHandler.repeat(this::getInputWinningLotto);
        WinningLotto winningLotto = RetryHandler.repeat(() -> getWinningLotto(inputWinningLotto));
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
