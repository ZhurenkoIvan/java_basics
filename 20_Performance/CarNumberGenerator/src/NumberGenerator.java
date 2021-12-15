import java.io.PrintWriter;

public class NumberGenerator implements Runnable{
    final PrintWriter writer;
    char[] letters;
    int regionCode;
    StringBuilder builder;
//    public static int threadsCount = 0;

    @Override
    public void run() {
        for (int number = 1; number < 1000; number++) {
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        padNumber(number, 3, builder);
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        padNumber(regionCode, 2, builder);
                        builder.append("\n");

                    }
                }
            }
            synchronized (writer) {
                writer.write(builder.toString());
                writer.flush();
            }
            builder.delete(0,builder.length());
        }

    }

    NumberGenerator (PrintWriter writer, char[] letters, int regionCode) {
        this.writer = writer;
        this.letters = letters;
        this.regionCode = regionCode;
        builder = new StringBuilder();
//        threadsCount++;
    }

    private String padNumber(int number, int numberLength, StringBuilder builder) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
           builder.append(0);
        }
        builder.append(number);

        return numberStr;
//        String numberStr = Integer.toString(number);
//        int padSize = numberLength - numberStr.length();
//
//        for (int i = 0; i < padSize; i++) {
//            numberStr = '0' + numberStr;
//        }
//
//        return numberStr;
    }
}
