package ControlePedido;

import java.util.List;

public class OrderItems {
    private Items item;
    private Integer amount;

    public OrderItems(Items item, Integer amount) {
        this.item = item;
        this.amount = amount;
    }

    public double calculatePrice() {
        return item.getPrice() * amount;
    }

    @Override
    public String toString() {
        return item + "Quantidade: " + "Subtotal: R$" + calculatePrice();
    }
}
