package ControlePedido;

public class Address {
    private String street;
    private String block;
    private String city;
    private String cep;

    public Address(String street, String block, String city, String cep) {
        this.street = street;
        this.block = block;
        this.city = city;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return street + ", " + block + ", " + city + ", " + cep;
    }
}
