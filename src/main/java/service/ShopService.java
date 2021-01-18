package service;

import dao.GoodsDAO;
import dao.OrderDAO;
import entity.Goods;
import entity.OrderOne;
import entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private GoodsDAO goodsDAO;
    @Transactional
    public void deleteOneOrder() {
        orderDAO.deleteOneOrder();
    }
    @Transactional
    public List<OrderOne> getOneOrder() {
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
    public List<Orders> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    @Transactional
    public String mainShopPage(String quantity, String button){
        List<Goods> list = goodsDAO.getAllGoods();
        List<OrderOne> listOrder = getOneOrder();
        int qua = Integer.parseInt(quantity);
        int but = Integer.parseInt(button);
        String a = null;
            for (Goods goods : list) {
                int id = goods.getId();
                String name = goods.getName();
                int price = goods.getPrice();
                if (qua > 0) {
                    if (but == id) {
                        for (OrderOne order : listOrder) {
                            if (goods.getName().equals(order.getName())) {
                                qua = qua + order.getQuantity();
                                if (qua > goods.getQuantity()) {
                                    qua = goods.getQuantity();
                                    a = "We have only " +
                                            goods.getQuantity() + " of " +
                                            goods.getName() + ". " +
                                            "We have to add max we have. <br>";
                                }
                                int idOrder = order.getId();
                                int sum = qua * order.getPrice();
                                editOneOrder(idOrder, qua, sum);
                            }
                        }
                    }
                    if (but == id) {
                        if (qua > goods.getQuantity()) {
                            a = "We have only " +
                                    goods.getQuantity() + " of " +
                                    goods.getName() + ". " +
                                    "We have to add max we have. <br>";
                            qua = goods.getQuantity();
                            int sum = qua * price;
                            addOneOrder(name, price, qua, sum);
                        } else {
                            int sum = qua * price;
                            addOneOrder(name, price, qua, sum);
                        }
                    }
                }
            } return a;
    }
    @Transactional
    public List<OrderOne> listOrder(){
        return getOneOrder().stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
    }
    @Transactional
    public int count(){
        return listOrder().stream().mapToInt(OrderOne::getSum).sum();
    }
    @Transactional
    public void choice(String login){
        List<OrderOne> list = orderDAO.getOneOrder().stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
        orderDAO.addOrders(login,list
                .toString().replace("{", "").replace("}", "")
                .replace(",", "").replace("[", "").replace("]", ""));
        List<OrderOne> listOrder = orderDAO.getOneOrder();
        List<Goods> listGoods = goodsDAO.getAllGoods();
        for (OrderOne order : listOrder) {
            int quantity = order.getQuantity();
            String name = order.getName();
            for (Goods goods : listGoods) {
                if (goods.getName().equals(name)) {
                    int id = goods.getId();
                    int result = goods.getQuantity() - quantity;
                    goodsDAO.changeGoods(id, result);
                }
            }
        }
        orderDAO.deleteOneOrder();
    }
    @Transactional
    public void editBasket(String quantity, String name){
        int qua = Integer.parseInt(quantity);
        List<OrderOne> list = orderDAO.getOneOrder();
        List<Goods> listGoods = goodsDAO.getAllGoods();
        int maxQuantity = 0;
        for (OrderOne order : list){
            if (order.getName().equals(name)) {
                for (Goods goods : listGoods) {
                    if (goods.getName().equals(name)) {
                        maxQuantity = goods.getQuantity();
                    }
                }
                if (qua >= 0) {
                    if (qua < maxQuantity) {
                        orderDAO.editOneOrder(order.getId(), qua, qua*order.getPrice());
                    } else if (qua > maxQuantity) {
                        orderDAO.editOneOrder(order.getId(), maxQuantity, qua*order.getPrice());
                    }
                }
            }
        }
    }

    @Transactional
    public List<OrderOne> editBasket(String button) {
        try{
            int but = Integer.parseInt(button);
            return orderDAO.getOneOrder().stream().filter(s->s.getId()==but).collect(Collectors.toList());
        } catch (NumberFormatException | IllegalFormatException e) {
            return null;
        }
    }
}
