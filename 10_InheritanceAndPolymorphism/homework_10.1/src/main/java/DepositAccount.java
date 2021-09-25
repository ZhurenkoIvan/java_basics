import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    public LocalDate lastIncome;

    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    public boolean take(double amountToTake) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (lastIncome.until(localDateTime, ChronoUnit.MONTHS) >= 1) {
            super.take(amountToTake);
        }
        return false;
    }
}
