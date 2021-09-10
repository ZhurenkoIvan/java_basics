public class Main {
    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(10, 3, 4);
        Cargo cargo = new Cargo(
                dimensions,
                3.5,
                "Saint-Petersburg",
                true,
                "142SDE03mfJ7",
                false
        );

        System.out.println(cargo.toString());

        Cargo cargo1 = cargo.setDeliveryAddress("Moscow");
        System.out.println(cargo1.toString());

    }
}
