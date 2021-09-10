
public class Cargo {
    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean reversible;
    private final String registrationNumber;
    private final boolean fragile;


    public Cargo(Dimensions dimensions, double weight, String deliveryAddress, boolean reversible, String registrationNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.reversible = reversible;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public Cargo setWeight (double weight) {
        return new Cargo (
                dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile
        );
    }

    public Cargo setDimensions (Dimensions dimensions) {
        return new Cargo (
                dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile
        );
    }

    public Cargo setDeliveryAddress (String deliveryAddress) {
        return new Cargo (
                dimensions,
                weight,
                deliveryAddress,
                reversible,
                registrationNumber,
                fragile
        );
    }

    public double getValue() {
        return dimensions.getValue();
    }
    public String toString () {
        return weight + deliveryAddress + reversible + fragile + registrationNumber;
    }


}
