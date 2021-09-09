public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40, 30);
        basket.add("Bread", 30, 70);
        basket.add("TV", 40000, 1500);
        basket.print("");
        System.out.println(basket.getTotalWeight());
        System.out.println(basket.getTotalPrice());


    }
}
