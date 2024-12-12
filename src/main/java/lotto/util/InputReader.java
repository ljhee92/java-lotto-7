package lotto.util;

import camp.nextstep.edu.missionutils.Console;

public interface InputReader {
    default String inputMessage() {
        return Console.readLine();
    }
}
