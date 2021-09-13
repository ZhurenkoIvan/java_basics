public class Main {
    public static void main(String[] args) {

        //Ввожу комплектующие компьютера
        Processor processor = new Processor(8,3.7, "AMD", 0.200);
        Hdd hdd = new Hdd(HDDType.SSD, 512, 0.5);
        Screen screen = new Screen(ScreenType.IPS, 50, 8.0);
        Ram ram = new Ram(RamType.DDR5, 0.4, 16);
        Keyboard keyboard = new Keyboard(KeyboardType.MECHANIC, true, 0.5);
        //=====================================================

        //Создаю компьютер и вывожу его содержимое в консоль
        Computer computer = new Computer(
                processor,
                ram,
                hdd,
                screen,
                keyboard,
                "IRL",
                "SG-574"
        );
        computer.toString();
        //=================================



    }
}
