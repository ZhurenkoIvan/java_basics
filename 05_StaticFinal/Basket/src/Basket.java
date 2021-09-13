public class Basket {

    private static int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double weight = 0;
    private static double totalWeight = 0;
    private static int allGoodsCount = 0;
    private static int totalPriceAllBaskets = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public static void increaseAllGoodsCount (int count) {
        Basket.allGoodsCount = Basket.allGoodsCount + count;
    }

    public static void increaseTotalPriceAllBaskets (int price, int count) {
        Basket.totalPriceAllBaskets = Basket.totalPriceAllBaskets + price * count;
    }

    public static double averagePriceAllBaskets () {
        return Basket.totalPriceAllBaskets / Basket.allGoodsCount;
    }

    public static double averageCheckAllBaskets () {
        return Basket.totalPriceAllBaskets / Basket.count;
    }

    public void add(String name, int price) {
        add(name, price, 0, 1);
    }

    public void add(String name, int price, double weight) {
        add(name, price, weight, 1);
    }

    public void add(String name, int price, double weight, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }
        this.weight = this.weight + weight;
        Basket.totalWeight = Basket.totalWeight + weight;

        items = items + "\n" + name + " - " +
                count + " шт. - " + price + " весом " + this.weight + " г.";
        totalPrice = totalPrice + count * price;
        increaseAllGoodsCount(count);
        increaseTotalPriceAllBaskets(price, count);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public static double getTotalWeight() {
        return totalWeight;
    }

    public double getWeight() {
        return weight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }


}
