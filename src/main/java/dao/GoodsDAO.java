package dao;

import entity.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GoodsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Goods> getAllGoods() {
        return sessionFactory.getCurrentSession().createQuery("FROM Goods", Goods.class).list();
    }

    public void addGoods(String name, String description, int quantity, int price) {
        sessionFactory.getCurrentSession().save(new Goods(name,description,quantity,price));
    }

    public void deleteGoods(int id) {
        Goods goods = sessionFactory.getCurrentSession().get(Goods.class, id);
        sessionFactory.getCurrentSession().delete(goods);
    }

    public void editGoods(int id, String name, String description, int quantity, int price) {
        Goods goods = sessionFactory.getCurrentSession().get(Goods.class, id);
        goods.setName(name);
        goods.setDescription(description);
        goods.setPrice(price);
        goods.setQuantity(quantity);
        sessionFactory.getCurrentSession().update(goods);
    }

    public void changeGoods(int id,int quantity) {
        Goods goods = sessionFactory.getCurrentSession().get(Goods.class, id);
        goods.setQuantity(quantity);
        sessionFactory.getCurrentSession().update(goods);
    }
}
