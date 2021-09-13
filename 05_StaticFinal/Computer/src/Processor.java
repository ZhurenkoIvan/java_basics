public class Processor {
    private final int cores;
    private final double frequency;
    private final String manufacturer;
    private final double weight;

    public Processor(int cores, double frequency, String manufacturer, double weight) {
        this.cores = cores;
        this.frequency = frequency;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public int getCores() {
        return cores;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }
}
