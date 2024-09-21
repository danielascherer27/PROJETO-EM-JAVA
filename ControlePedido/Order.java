package ControlePedido;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer number;
    private List<OrderItems> orderItems;
    private Person client;
    private Address address;

    public Order(Integer number, Person client, Address address) {
        this.number = number;
        this.orderItems = new ArrayList<>();
        this.client = client;
        this.address = address;
    }

    public void addOrderItem(OrderItems item) {
        this.orderItems.add(item);
    }

    public void removeOrderItem(OrderItems item) {
        this.orderItems.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItems item : orderItems) {
            total += item.calculatePrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido número: ").append(this.number).append("\n");
        sb.append("Cliente: ").append(this.client).append("\n");
        sb.append("Endereço: ").append(this.address).append("\n");
        for (OrderItems item : orderItems) {
            sb.append(item).append("\n");
        }
        sb.append("Total: R$").append(this.calculateTotal()).append("\n");
        return sb.toString();
    }
}
