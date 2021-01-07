package entity;

import javax.persistence.*;

@Entity
@Table (name = "orderone")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int sum;

    public Order(int id, String name, int price, int quantity, int sum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }

    public Order() {
    }

    public Order(String name, int price, int quantity, int sum) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sum=" + sum + "<br>";
    }
}
