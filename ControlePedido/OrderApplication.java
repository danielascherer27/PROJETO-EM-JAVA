package ControlePedido;public class OrderApplication {
    public static void main(String[] args) {
        Items item1 = new Items("Chocolate", 7.50);
        Items item2 = new Items("Sanduíche", 8.50);
        Items item3 = new Items("Bolo", 6.50);

        Address address = new Address("Tancredo Neves", "Pancera", "Toledo", "85902-312");
        Person client1 = new Person("Guilherme", address);

        Order order1 = new Order(1, client1, address);

        order1.addOrderItem(new OrderItems(item1, 1));
        order1.addOrderItem(new OrderItems(item2, 2));
        order1.addOrderItem(new OrderItems(item3, 2));

        System.out.println(order1);
    }
}
