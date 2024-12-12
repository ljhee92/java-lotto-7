package lotto.domain;

import java.util.Map;

public record StatisticsInfo(int fifth, int forth, int third, int second, int first) {
    public static StatisticsInfo of(Map<Statistics, Integer> result) {
        return new StatisticsInfo(result.get(Statistics.FIFTH), result.get(Statistics.FOURTH),
                result.get(Statistics.THIRD), result.get(Statistics.SECOND), result.get(Statistics.FIRST));
    }
}
