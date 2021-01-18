package service;

import dao.GoodsDAO;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    private GoodsDAO goodsDAO;

    @Autowired
    public void setGoodsDAO(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    @Transactional
    public List<Goods> getAllGoods() {
        return goodsDAO.getAllGoods();
    }

    @Transactional
    public List<Goods> notNullAllGoods() {
        return goodsDAO.getAllGoods().stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
    }

    @Transactional
    public boolean addGoods(String name, String description, String quantity, String price) {
        try {
            int qua = Integer.parseInt(quantity);
            int pri = Integer.parseInt(price);
            if (qua > 0 && pri > 0 && !name.trim().equals("")
                    && !description.trim().equals("")) {
                goodsDAO.addGoods(name,description,qua,pri);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | IllegalFormatException e){
            return false;
        }
    }

    @Transactional
    public void deleteGoods(String button) {
        List<Goods> list = getAllGoods();
        for (int i = 0; i< list.size(); i++) {
            int id = list.get(i).getId();
            if (button.equals("Delete " + i)) {
                goodsDAO.deleteGoods(id);
            }
        }
    }

    @Transactional
    public boolean editGoods(String id, String name, String description, String quantity, String price) {
        List<Goods> list = getAllGoods();
        try{
            int i = Integer.parseInt(id);
            int qua = Integer.parseInt(quantity);
            int pri = Integer.parseInt(price);
            if (i > 0 || pri > 0 || qua > 0){
                for (Goods goods : list) {
                    if (goods.getId() == i) {
                        goodsDAO.editGoods(i, name, description, qua, pri);
                        return true;
                    }
                }
            }
        } catch (NumberFormatException | IllegalFormatException e) {
            return false;
        } return false;
    }
}
