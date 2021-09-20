import java.util.*;

public class CoolNumbers {
    public static final String LETTERS = "[АВЕКМНОРСТУХ]";
    public static final String CAR_NUMBER_REGEX = LETTERS + "[0-9]{3}" + LETTERS + "{2}[0-9]{2,3}";

    public static List<String> generateCoolNumbers() {
        ArrayList<String> coolNumbers = new ArrayList<>(5000000);
        while (coolNumbers.size() < 5000000) {
            coolNumbers.add(getCarNumber());
        }
        return coolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        if (list.contains(number)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int result = Collections.binarySearch(sortedList, number);
        if (result < 0) {
            return false;
        }else {
            return true;
        }
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        if (hashSet.contains(number)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        if (treeSet.contains(number)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getLastDigits() {
        String stringDigit;
        double digit = Math.random() * 198;
        int roundedDigit = (int) Math.round(digit) + 1;
        if (roundedDigit < 10)
        {
            stringDigit = "0" + roundedDigit;
        }
        else {
            stringDigit = String.valueOf(roundedDigit);
        };
        return stringDigit;
    }
    public static String getDigit(int count) {
        String digits = "";
        for (int i = 0; i < count; i++) {
            double digit = Math.random() * 9;
            int roundedDigit = (int) Math.round(digit);
            digits += roundedDigit;
            if (digits.equals("000")){
                digits = "001";
            }
        }
        return digits;
    }

    public static String getLetter() {
        String finalLetter = "";
        double letter = 0.0;
        char roundedLetter =' ';
        while (!finalLetter.matches(LETTERS)) {
            letter = 1040 + Math.random() * 21;
            roundedLetter = (char) Math.round(letter);
            finalLetter = String.valueOf(roundedLetter);
        }
        return finalLetter;

    }

    public static String getCarNumber () {
        String carNumber = getLetter() + getDigit(3) +
                getLetter() + getLetter() + getLastDigits();
        return carNumber;
    }

}
