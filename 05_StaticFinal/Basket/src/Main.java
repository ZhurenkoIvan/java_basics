public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        Basket basket1 = new Basket();
        basket.add("Milk", 40, 30);
        basket.add("Bread", 30, 70);
        basket.add("TV", 40000, 1500);
        basket.print("");
        basket1.add("Drink", 1000, 2.5);
        basket1.add("Box", 12000, 10);
        System.out.println(basket.getWeight());
        System.out.println(basket.getTotalPrice());
        System.out.println(Basket.getCount());
        System.out.println(Basket.averageCheckAllBaskets());
        System.out.println(Basket.averagePriceAllBaskets());


    }
}
