import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    public LocalDate lastIncome;
    public static final double COMMISSION = 1.0;


    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    public boolean take(double amountToTake, double commission) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (lastIncome.until(localDateTime, ChronoUnit.MONTHS) >= 1) {
            super.take(amountToTake, commission);
        }
        return false;
    }
}
