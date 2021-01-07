package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "orders")
public class Orders {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String users;
    private String orders;
    private Date date;

    public Orders() {
    }

    public Orders(String users, String orders, Date date) {
        this.users = users;
        this.orders = orders;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", users='" + users + '\'' +
                ", orders='" + orders + '\'' +
                ", date='" + date + '\'';
    }
}
