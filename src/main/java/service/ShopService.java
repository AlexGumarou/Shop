package service;

import dao.OrderDAO;
import entity.Order;
import entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private OrderDAO orderDAO;
    @Transactional
    public void deleteOneOrder() {
        orderDAO.deleteOneOrder();
    }
    @Transactional
    public List<Order> getOneOrder() {
        return orderDAO.getOneOrder();
    }
    @Transactional
    public void addOneOrder(String name, int price, int quantity, int sum) {
        orderDAO.addOneOrder(name, price, quantity, sum);
    }
    @Transactional
    public void editOneOrder(int id, int quantity, int sum) {
        orderDAO.editOneOrder(id, quantity, sum);
    }
    @Transactional
    public void addOrders(String users, String orders) {
        orderDAO.addOrders(users, orders);
    }
    @Transactional
    public List<Orders> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}
