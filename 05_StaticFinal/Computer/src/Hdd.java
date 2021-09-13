public class Hdd {
    private final HDDType type;
    private final int memory;
    private final double weight;

    public Hdd(HDDType type, int memory, double weight) {
        this.type = type;
        this.memory = memory;
        this.weight = weight;
    }

    public HDDType getType() {
        return type;
    }

    public int getMemory() {
        return memory;
    }

    public double getWeight() {
        return weight;
    }
}
