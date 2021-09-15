
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        int day = 12;
        int month = 9;
        int year = 2021;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        LocalDate myBirthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        int yearCount = 0;
        String allDates = "";
        while (myBirthday.isBefore(today) || myBirthday.isEqual(today)) {
            allDates = allDates + rememberDate(myBirthday, yearCount);
            myBirthday = myBirthday.plusYears(1);
            yearCount++;
        }
        return allDates;



        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
    }

    public static String rememberDate (LocalDate localDate, int year) {
        String dayOfWeek = getDayOfWeek(localDate.getDayOfWeek());
        String date = year + " - " + FORMATTER.format(localDate) + " - " + dayOfWeek + System.lineSeparator();
        return date;
    }

    private static String getDayOfWeek(DayOfWeek dayOfWeek) {

        if (dayOfWeek.toString() == "MONDAY") {return "Mon";}
        if (dayOfWeek.toString() == "TUESDAY") {return "Tue";}
        if (dayOfWeek.toString() == "SUNDAY") {return "Sun";}
        if (dayOfWeek.toString() == "WEDNESDAY") {return "Wed";}
        if (dayOfWeek.toString() == "FRIDAY") {return "Fri";}
        if (dayOfWeek.toString() == "THURSDAY") {return "Thu";}
        else {return "Sat";}
    }
}
