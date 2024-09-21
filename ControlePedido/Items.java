package ControlePedido;

public class Items {
    private String name;
    private Double price;

    public Items(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - R$ " + price;
    }
}
