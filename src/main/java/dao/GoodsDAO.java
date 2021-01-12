package dao;

import entity.Goods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import config.HibernateUtil;
import java.util.List;

@Controller
public class GoodsDAO {
    Transaction transaction = null;

    public List<Goods> getAllGoods() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Goods> list = session.createQuery("from Goods", Goods.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }

    public void addGoods(String name, String description, int quantity, int price) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new Goods(name,description,quantity,price));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGoods(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Goods goods = session.get(Goods.class, id);
            session.delete(goods);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editGoods(int id, String name, String description, int quantity, int price) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Goods goods = session.get(Goods.class, id);
            goods.setName(name);
            goods.setDescription(description);
            goods.setPrice(price);
            goods.setQuantity(quantity);
            session.update(goods);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeGoods(int id,int quantity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Goods goods = session.get(Goods.class, id);
            goods.setQuantity(quantity);
            session.update(goods);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
