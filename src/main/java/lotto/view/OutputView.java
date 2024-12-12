package lotto.view;

import lotto.domain.LottoInfo;
import lotto.util.OutputWriter;

import java.util.List;

public class OutputView implements OutputWriter {
    public void displayPurchaseResult(List<LottoInfo> lottos, int ticketCount) {
        displayFormat("%d개를 구매했습니다.", ticketCount);
        lottos.forEach(lottoInfo -> {
            displayMessageByLine(lottoInfo.numbers().toString());
        });
    }
}
