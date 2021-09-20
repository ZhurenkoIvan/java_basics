import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        List<String> list = CoolNumbers.generateCoolNumbers();

        //Сортировка перебором
        double start = System.nanoTime();
        if (CoolNumbers.bruteForceSearchInList(list, number)) {
            System.out.println("Номер найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        } else{
            System.out.println("Номер не найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        }

        //Бинарный поиск
        Collections.sort(list);
        start = System.nanoTime();
        if (CoolNumbers.binarySearchInList(list, number)) {
            System.out.println("Номер найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        } else{
            System.out.println("Номер не найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        }

        //Поиск в Хэшсет
        HashSet<String> hashSet= new HashSet<>(list);
        start = System.nanoTime();
        if (CoolNumbers.searchInHashSet(hashSet, number)) {
            System.out.println("Номер найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        } else{
            System.out.println("Номер не найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        }

        //Поиск в Трисет
        TreeSet<String> treeSet = new TreeSet<>(list);
        start = System.nanoTime();
        if (CoolNumbers.searchInTreeSet(treeSet, number)) {
            System.out.println("Номер найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        } else{
            System.out.println("Номер не найден. Поиск занял " + (System.nanoTime() - start) + "нс");
        }



    }
}
