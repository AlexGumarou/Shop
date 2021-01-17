package service;

import dao.GoodsDAO;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Transactional
    public List<Goods> getAllGoods() {
        return goodsDAO.getAllGoods();
    }

    @Transactional
    public void addGoods(String name, String description, int quantity, int price) {
        goodsDAO.addGoods(name, description, quantity, price);
    }

    @Transactional
    public void deleteGoods(int id) {
        goodsDAO.deleteGoods(id);
    }

    @Transactional
    public void editGoods(int id, String name, String description, int quantity, int price) {
        goodsDAO.editGoods(id, name, description, quantity, price);
    }

    @Transactional
    public void changeGoods(int id,int quantity) {
        goodsDAO.changeGoods(id, quantity);
    }
}
