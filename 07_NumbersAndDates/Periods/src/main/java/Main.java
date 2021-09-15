import java.time.LocalDate;
import java.time.Period;

public class Main {

  public static void main(String[] args) {
    LocalDate localDate = LocalDate.of(1996, 1, 21);
    System.out.println(getPeriodFromBirthday(localDate));
  }

  private static String getPeriodFromBirthday(LocalDate birthday) {
    Period period = Period.between(birthday, LocalDate.now());
    return period.getYears() + " years, " + period.getMonths() + " months, " + period.getDays() + " days";
  }

}
