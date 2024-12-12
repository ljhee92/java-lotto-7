package lotto.view;

import lotto.domain.LottoInfo;
import lotto.domain.StatisticsInfo;
import lotto.util.OutputWriter;

import java.util.List;

public class OutputView implements OutputWriter {
    private static final String PURCHASE_RESULT_FORMAT = "%n%d개를 구매했습니다.";
    private static final String STATISTICS_HEAD = "당첨 통계%n---";
    private static final String PROFIT_FORMAT = "총 수익률은 %,.1f%%입니다.";

    public void displayPurchaseResult(List<LottoInfo> lottos, int ticketCount) {
        displayFormat(PURCHASE_RESULT_FORMAT, ticketCount);
        lottos.forEach(lottoInfo -> {
            displayMessageByLine(lottoInfo.numbers().toString());
        });
    }

    public void displayStatistics(StatisticsInfo statisticsInfo) {
        displayNewLine();
        displayFormat(STATISTICS_HEAD);
        displayFormat("3개 일치 (5,000원) - %d개", statisticsInfo.fifth());
        displayFormat("4개 일치 (50,000원) - %d개", statisticsInfo.forth());
        displayFormat("5개 일치 (1,500,000원) - %d개", statisticsInfo.third());
        displayFormat("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개", statisticsInfo.second());
        displayFormat("6개 일치 (2,000,000,000원) - %d개", statisticsInfo.first());
    }

    public void displayProfit(double profit) {
        displayFormat(PROFIT_FORMAT, profit);
    }
}
