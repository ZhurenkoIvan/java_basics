import java.util.Comparator;

public class EmailComparator implements Comparator<String> {


    @Override
    public int compare(String email1, String email2) {
        if (email1.equals(email2)) {
            return 0;
        }
        int dogIndex1 = email1.lastIndexOf('@');
        String domen1 = email1.substring(dogIndex1 + 1);

        int dogIndex2 = email2.lastIndexOf('@');
        String domen2 = email2.substring(dogIndex2 + 1);

        return firstEmailByDomen(domen1, domen2);
    }

    private char[] devideLetters(String email) {
        char[] lettersOfEmail = new char[email.length()];
        for (int i = 0; i < email.length(); i++) {
            lettersOfEmail[i] = email.charAt(i);
        }
        return lettersOfEmail;
    }

    private int firstEmailByDomen(String domen1, String domen2) {
        char[] lettersOfEmail1 = devideLetters(domen1);
        char[] lettersOfEmail2 = devideLetters(domen2);

        for (int i = 0; i < lettersOfEmail1.length; i++) {
            if (lettersOfEmail1[i] == lettersOfEmail2[i]) {}
            else if (lettersOfEmail1[i] < lettersOfEmail2[i]){
                return -1;
            } else {
                return 1;
            }
        }
        return -1;
    }
}
