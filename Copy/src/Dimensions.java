public class Dimensions {
    private final double length;
    private final double width;
    private final double height;

    public Dimensions (double length, double width, double height) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double getValue () {
        return height * width * length;
    }

}
