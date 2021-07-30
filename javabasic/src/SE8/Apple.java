package SE8;

public class Apple {

    private Double weight;

    private String color;


    public Apple(Double weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(String color, Double weight) {
        this.weight = weight;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
