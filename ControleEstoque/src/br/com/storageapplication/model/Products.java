package src.br.com.storageapplication.model;

public class Products {
    private int id; //yet not defined
    private String name;
    private String description;
    private Boolean available;
    private Integer quantity;
    private ProductCategory category;
    private ProductUnitType unitType;

    //Constructors
    public Products(int id, String name, Boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public Products(int id, String name, String description, Boolean available, Integer quantity, ProductCategory category, ProductUnitType unitType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.quantity = quantity;
        this.category = category;
        this.unitType = unitType;
    }
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductUnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(ProductUnitType unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", quantity=" + quantity +
                ", category=" + category +
                ", unitType=" + unitType +
                '}';
    }
}
