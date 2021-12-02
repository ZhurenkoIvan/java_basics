import java.io.PrintWriter;

public class NumberGenerator implements Runnable{
    final PrintWriter writer;
    char[] letters;
    int regionCode;

    @Override
    public void run() {
        for (int number = 1; number < 1000; number++) {
            StringBuilder builder = new StringBuilder();
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");

                    }
                }
            }
            synchronized (writer) {
                writer.write(builder.toString());
                writer.flush();
            }
        }

    }

    NumberGenerator (PrintWriter writer, char[] letters, int regionCode) {
        this.writer = writer;
        this.letters = letters;
        this.regionCode = regionCode;
    }

    private String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }
}
