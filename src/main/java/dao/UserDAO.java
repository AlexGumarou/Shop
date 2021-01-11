package dao;

import entity.PersonalData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import util.HibernateUtil;
import java.util.List;

@Controller
public class UserDAO {
    Transaction transaction = null;

    public List<PersonalData> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<PersonalData> list = session.createQuery("from PersonalData", PersonalData.class).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }

    public boolean isUser(String login, String pass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<PersonalData> list = getAllUsers();
            long size = list.stream().filter(s->s.getLogin().equals(login) && s.getPass().equals(pass)).count();
            if (size == 1){
                return true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } return false;
    }

    public List<PersonalData> getCurrentUser(String login, String pass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<PersonalData> list = session.createQuery("from PersonalData p where p.login = :login and p.pass = :pass")
                    .setParameter("login", login)
                    .setParameter("pass", pass).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;

    }

    public void deleteUser(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PersonalData personalData = session.get(PersonalData.class, id);
            session.delete(personalData);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String login, String pass, String name, String surname) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new PersonalData(login,pass,name,surname));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAdditionalFields(int id, String address, String email, String phone) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PersonalData personalData = session.get(PersonalData.class, id);
            personalData.setAddress(address);
            personalData.setEmail(email);
            personalData.setPhone(phone);
            session.update(personalData);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
