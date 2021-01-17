package dao;

import entity.PersonalData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<PersonalData> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM PersonalData", PersonalData.class).list();
    }

    public boolean isUser(String login, String pass) {
        List<PersonalData> list = getAllUsers();
        long size = list.stream().filter(s->s.getLogin().equals(login) && s.getPass().equals(pass)).count();
        return size == 1;
    }

    public List<PersonalData> getCurrentUser(String login, String pass) {
        return (List<PersonalData>) sessionFactory.getCurrentSession().createQuery("from PersonalData p where " +
                "p.login = :login and p.pass = :pass")
                .setParameter("login", login)
                .setParameter("pass", pass).list();
    }

    public void deleteUser(int id) {
        PersonalData personalData = sessionFactory.getCurrentSession().get(PersonalData.class, id);
        sessionFactory.getCurrentSession().delete(personalData);
    }

    public void addUser(String login, String pass, String name, String surname) {
        sessionFactory.getCurrentSession().save(new PersonalData(login,pass,name,surname));
    }

    public void setAdditionalFields(int id, String address, String email, String phone) {
        PersonalData personalData = sessionFactory.getCurrentSession().get(PersonalData.class, id);
        personalData.setAddress(address);
        personalData.setEmail(email);
        personalData.setPhone(phone);
        sessionFactory.getCurrentSession().update(personalData);
    }
}
