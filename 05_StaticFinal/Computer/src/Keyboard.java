public class Keyboard {
    private final KeyboardType type;
    private final boolean rgb;
    private final double weight;

    public Keyboard(KeyboardType type, boolean rgb, double weight) {
        this.type = type;
        this.rgb = rgb;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public boolean isRgb() {
        return rgb;
    }

    public double getWeight() {
        return weight;
    }
}
