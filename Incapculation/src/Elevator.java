public class Elevator {

    private int minFloor = 0;
    private int maxFloor = 0;
    private int currentFloor = 1;

    public Elevator(int minFloor, int maxFloor) {
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    private void moveUp() {
        currentFloor = currentFloor + 1;
    }

    private void moveDown() {
        currentFloor = currentFloor - 1;
    }

    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Неверно выбран этаж. Выберите этаж от " + minFloor + " до " + maxFloor);
            return;
        } else if (floor < currentFloor) {
            while (currentFloor != floor){
                moveDown();
                System.out.println("Текущий этаж: " + currentFloor);
            }
        } else {
            while (currentFloor != floor) {
                moveUp();
                System.out.println("Текущий этаж: " + currentFloor);
            }
        }
        System.out.println("Вы приехали.");
    }
}
