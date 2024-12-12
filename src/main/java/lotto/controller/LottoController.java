package lotto.controller;

import lotto.Lotto;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Money purchaseAmount = RetryHandler.repeat(this::getPurchaseAmount);
        int ticketCount = purchaseAmount.getTicketCount();

        lottoService.issueLottos(ticketCount);
        outputView.displayPurchaseResult(lottoService.getLottoInfos(), ticketCount);

        Lotto inputWinningLotto = RetryHandler.repeat(this::getInputWinningLotto);
        WinningLotto winningLotto = RetryHandler.repeat(() -> getWinningLotto(inputWinningLotto));

        lottoService.draw(winningLotto);
        outputView.displayStatistics(lottoService.getStatisticsInfo());

        outputView.displayProfit(lottoService.getProfit(purchaseAmount));
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
