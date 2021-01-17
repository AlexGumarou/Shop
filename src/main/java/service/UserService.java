package service;

import dao.UserDAO;
import entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<PersonalData> getAllUsers() {
        return userDAO.getAllUsers();
    }
    @Transactional
    public boolean isUser(String login, String pass) {
        return userDAO.isUser(login,pass);
    }
    @Transactional
    public void addUser(String login, String pass, String name, String surname) {
        userDAO.addUser(login, pass, name, surname);
    }
    @Transactional
    public List<PersonalData> getCurrentUser(String login, String pass) {
        return userDAO.getCurrentUser(login, pass);
    }
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
    @Transactional
    public void setAdditionalFields(int id, String address, String email, String phone) {
        userDAO.setAdditionalFields(id, address, email, phone);
    }
}
