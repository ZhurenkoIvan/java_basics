import java.util.Scanner;

public class Main {
    public static final int MAX_BOXES_IN_CONTAINER = 27;
    public static final int MAX_CONTAINERS_IN_CARGO = 12;

    private static int cargoNumber = 1;
    private static int containerNumber = 1;
    private static int boxNumber = 1;

    private static void clearVariables () {
        Main.cargoNumber = 1;
        Main.containerNumber = 1;
        Main.boxNumber = 1;
    }

    public static void boxNumberPrint (int boxNumber) {
            System.out.print("\t\t");
            System.out.println("Ящик: " + boxNumber);
            Main.boxNumber++;
    }

    public static void containerNumberPrint (int containerNumber) {
        System.out.print("\t");
        System.out.println("Контейнер: " + containerNumber);
        Main.containerNumber++;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int boxesCount = Integer.parseInt(boxes);
        int containersCount = boxesCount % MAX_BOXES_IN_CONTAINER == 0 ?
                boxesCount / MAX_BOXES_IN_CONTAINER :
                boxesCount / MAX_BOXES_IN_CONTAINER + 1;
        int cargosCount = containersCount % MAX_CONTAINERS_IN_CARGO == 0 ?
                containersCount / MAX_CONTAINERS_IN_CARGO :
                containersCount / MAX_CONTAINERS_IN_CARGO + 1;

        int cargosCount1 = cargosCount;
        int containersCount1 = containersCount;



        while (cargosCount > 1) {
            System.out.println("Грузовик: " + cargoNumber);
            cargosCount--;
            cargoNumber++;
            for (int j = 1; j <= MAX_CONTAINERS_IN_CARGO; j++) {
                containerNumberPrint(containerNumber);
                containersCount--;
                for (int k = 1; k <= MAX_BOXES_IN_CONTAINER; k++) {
                    boxNumberPrint(boxNumber);
                    boxesCount--;
                }
            }
        }
        if (cargosCount != 0) {
            System.out.println("Грузовик: " + cargoNumber);
            for (int j = 1; j <= containersCount; j++) {
                containerNumberPrint(containerNumber);
                if (boxesCount > MAX_BOXES_IN_CONTAINER) {
                    for (int k = 1; k <= MAX_BOXES_IN_CONTAINER; k++) {
                        boxNumberPrint(boxNumber);
                        boxesCount--;
                    }
                } else {
                    for (int k = 1; k <= boxesCount; k++) {
                        boxNumberPrint(boxNumber);
                    }

                }


            }
        }
        System.out.println("Необходимо:" + System.lineSeparator() +
                "грузовиков - " + cargosCount1 + " шт." + System.lineSeparator() +
                "контейнеров - " + containersCount1 + " шт.");
        clearVariables();
    }
}
