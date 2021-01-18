package dao;

import entity.OrderOne;
import entity.Orders;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Orders> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Orders", Orders.class).list();
    }

    public List<OrderOne> getOneOrder() {
        return sessionFactory.getCurrentSession().createQuery("from OrderOne", OrderOne.class).list();
    }

    public void addOneOrder(String name, int price, int quantity, int sum) {
        sessionFactory.getCurrentSession().save(new OrderOne(name,price,quantity,sum));
    }

    public void editOneOrder(int id, int quantity, int sum) {
        OrderOne order = sessionFactory.getCurrentSession().get(OrderOne.class, id);
        order.setQuantity(quantity);
        order.setSum(sum);
        sessionFactory.getCurrentSession().update(order);
    }

    public void deleteOneOrder() {
        sessionFactory.getCurrentSession().createQuery("delete from OrderOne").executeUpdate();
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public void addOrders(String users, String orders) {
        sessionFactory.getCurrentSession().save(new Orders(users, orders, getCurrentDate()));
    }
}
