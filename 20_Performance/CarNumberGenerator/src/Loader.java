import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        PrintWriter writer = new PrintWriter("res/numbers.txt");


        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int regionCode = 1; regionCode < 10; regionCode++) {
            NumberGenerator numberGenerator = new NumberGenerator(writer, letters, regionCode);
            executor.execute(numberGenerator);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}
        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
//        System.out.println(NumberGenerator.threadsCount);
    }
}
