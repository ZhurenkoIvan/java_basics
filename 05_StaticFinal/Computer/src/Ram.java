public class Ram {
    private final RamType type;
    private final double weight;
    private final int memory;

    public Ram(RamType type, double weight, int memory) {
        this.memory = memory;
        this.type = type;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
    public int getMemory() {
        return memory;
    }

    public RamType getType() {
        return type;
    }
}
