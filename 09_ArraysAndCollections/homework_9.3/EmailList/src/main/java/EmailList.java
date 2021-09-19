import java.util.*;

public class EmailList {
    private TreeSet <String> emailList;
    public static final String WRONG_EMAIL = "Неверный формат email";
    public static  final String REGEX = "[A-Za-z]+[@]{1}[a-z]+[.]{1}[A-Za-z]+";

    public EmailList() {
        this.emailList = new TreeSet<>();
    }

    public void add(String email) {
        if(email.matches(REGEX)) {
            email = email.toLowerCase(Locale.ROOT);
            emailList.add(email);
        }
        else {
            System.out.println(WRONG_EMAIL);
        }

        // TODO: валидный формат email добавляется
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        return new ArrayList<>(emailList);
    }

}
