package lotto.domain;

import lotto.util.ErrorMessage;

import java.math.BigDecimal;

public class Money {
    private static final BigDecimal TICKET_UNIT = BigDecimal.valueOf(1000);
    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(BigDecimal amount) {
        validate(amount);
        return new Money(amount);
    }

    private static void validate(BigDecimal amount) {
        if (!amount.remainder(TICKET_UNIT).equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage());
        }
    }

    public int getTicketCount() {
        return this.amount.divide(TICKET_UNIT).intValue();
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
