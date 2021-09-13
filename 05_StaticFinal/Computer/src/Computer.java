public class Computer {

    //Изменяемые переменные
    private Processor processor;
    private Ram ram;
    private Hdd hdd;
    private Screen screen;
    private Keyboard keyboard;

    //Неизменяемые переменные
    private final String vendor;
    private final String name;


    //Конструктор, геттеры, сеттеры

    public Computer(
            Processor processor,
            Ram ram,
            Hdd hdd,
            Screen screen,
            Keyboard keyboard,
            String vendor,
            String name
    ) {
        this.processor = processor;
        this.ram = ram;
        this.hdd = hdd;
        this.screen = screen;
        this.keyboard = keyboard;
        this.vendor = vendor;
        this.name = name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Hdd getHdd() {
        return hdd;
    }

    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }
    //=========================================================

    //Метод для расчета веса всех комплектующих
    public double getTotalWeight() {
        return processor.getWeight()
                + ram.getWeight()
                + hdd.getWeight()
                + screen.getWeight()
                + keyboard.getWeight();
    }
    //================================================

    //Метод для вывода в консоль характеристик
    public String toString(){
        System.out.println("Характеристики компьютера " + name + " от компании " + vendor + ":");
        System.out.println("Процессор - " + processor.getManufacturer()
                + ", частота " + processor.getFrequency() + " ГГц"
                + ", количество ядер " + processor.getCores()
        );
        System.out.println("Тип накопителя - " + hdd.getType()
                + ", объем памяти " + hdd.getMemory() + " гб"
        );
        System.out.println("Клавитаура - " + keyboard.getType()
                + ", подсветка " + (keyboard.isRgb() ? "RGB" : "отсутствует")
        );
        System.out.println("Оперативная память - " + ram.getType()
                + ", объем оперативной памяти " + ram.getMemory() + " гб"
        );
        System.out.println("Экран. Тип матрицы - " + screen.getScreenType()
                + ", диагональ экрана " + screen.getDiagonal() + " дюймов"
        );
        System.out.println("Общий вес компонентов - " + getTotalWeight() + " кг");
        return null;
    }
    //==============================================================






}
