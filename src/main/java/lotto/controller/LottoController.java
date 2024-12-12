package lotto.controller;

import lotto.domain.Money;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money purchaseAmount = RetryHandler.repeat(this::getPurchaseAmount);
    }

    private Money getPurchaseAmount() {
        return Money.of(inputView.requestPurchaseAmount());
    }
}
