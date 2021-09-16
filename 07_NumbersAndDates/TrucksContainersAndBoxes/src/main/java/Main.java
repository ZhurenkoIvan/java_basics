import java.util.Scanner;

public class Main {
    public static final int MAX_BOXES_IN_CONTAINER = 27;
    public static final int MAX_BOXES_IN_CARGO = 324;

    private static int cargoNumber = 0;
    private static int containerNumber = 0;

    private static void clearVariables () {
        Main.cargoNumber = 0;
        Main.containerNumber = 0;
    }

    public static void boxNumberPrint (int boxNumber) {
            System.out.print("\t\t");
            System.out.println("Ящик: " + boxNumber);
    }

    public static void containerNumberPrint (int containerNumber) {
        Main.containerNumber++;
        System.out.print("\t");
        containerNumber++;
        System.out.println("Контейнер: " + containerNumber);


    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int boxesCount = Integer.parseInt(boxes);

        for (int i = 1; i <= boxesCount; i++) {
            if ((i-1) % MAX_BOXES_IN_CARGO == 0 || i == 1) {
                cargoNumber++;
                System.out.println("Грузовик: " + cargoNumber);
                containerNumberPrint(containerNumber);
                boxNumberPrint(i);
            }else if ((i-1) % MAX_BOXES_IN_CONTAINER == 0) {
                containerNumberPrint(containerNumber);
                boxNumberPrint(i);
            } else {
                boxNumberPrint(i);
            }
        }


        System.out.println("Необходимо:" + System.lineSeparator() +
                "грузовиков - " + cargoNumber + " шт." + System.lineSeparator() +
                "контейнеров - " + containerNumber + " шт.");
        clearVariables();
    }
}
