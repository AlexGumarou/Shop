package controller;

import dao.OrderDAO;
import dao.UserDAO;
import entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class UserControllers {
    @Autowired
    UserDAO userDao;

    @Autowired
    OrderDAO orderDAO;

    @ModelAttribute("user")
    public PersonalData getUser(){
        return new PersonalData();
    }

    @GetMapping(value = "/app")
    public String start(){
        return "index";
    }

    @GetMapping(value = "registration")
    public String getRegistration() {
        return "user/registrationPage";
    }

    @PostMapping(value = "registration")
    public String postRegistration(@ModelAttribute("user") @Valid PersonalData user, BindingResult result){
        if (result.hasErrors()){
            return "user/registrationPage";
        }
        if (userDao.isUser(user.getLogin(), user.getPass()) || user.getLogin().trim().equals("")
                || user.getPass().trim().equals("") || user.getName().trim().equals("")){
            return "redirect:/registrationIncorrect";
        } else {
            userDao.addUser(user.getLogin(), user.getPass(), user.getName(), user.getSurname());
            return "redirect:/app";
        }
    }

    @GetMapping(value = "registrationIncorrect")
    public String getIncorrectRegistration() {
        return "user/registrationPageIncorrect";
    }

    @PostMapping(value = "registrationIncorrect")
    public String RegistrationIncorrect(@ModelAttribute("user") @Valid PersonalData user, BindingResult result) {
        if (result.hasErrors()){
            return "user/registrationPageIncorrect";
        }
        return postRegistration(user,result);
    }

    @RequestMapping(path={"login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String login(@ModelAttribute("user") @Valid PersonalData user, BindingResult result,
                        HttpSession session){
        if (result.hasErrors()){
            return "index";
        }
        List<PersonalData> list = userDao.getCurrentUser(user.getLogin(),user.getPass());
        if (userDao.isUser(user.getLogin(),user.getPass())){
            orderDAO.deleteOneOrder();
            session.setAttribute("login", list.get(0).getLogin());
            session.setAttribute("pass", list.get(0).getPass());
            session.setAttribute("nameUser", list.get(0).getName());
            session.setAttribute("surname", list.get(0).getSurname());
            session.setAttribute("address",list.get(0).getAddress());
            session.setAttribute("email",list.get(0).getEmail());
            session.setAttribute("phone",list.get(0).getPhone());
            return "redirect:/mainWindow";
        } else {
            return "redirect:/incorrect";
        }
    }

    @RequestMapping(value = "mainWindow",  method = {RequestMethod.POST, RequestMethod.GET})
    public String getMainWindow() {
        return "user/mainWindow";
    }

    @GetMapping(value = "incorrect")
    public String getIncorrectLoginOrPass() {
        return "user/incorrectLoginOrPass";
    }

    @GetMapping(value = "dataCheck")
    public String dataCheck(HttpSession session){
        if (Objects.equals(session.getAttribute("nameUser"), userDao.getAllUsers().get(0).getName())){
            return "redirect:/dataAdmin";
        } else {
            return "redirect:/data";
        }
    }

    @GetMapping(value = "data")
    public String userData(){
        return "user/dataPage";
    }

    @GetMapping(value = "dataAdmin")
    public String userDataAdmin(){
        return "user/dataPageAdmin";
    }

    @RequestMapping(value = "userStore", method = {RequestMethod.POST, RequestMethod.GET})
    public String userStore(Model model){
        List<PersonalData> list = userDao.getAllUsers();
        model.addAttribute("list", list);
        model.addAttribute("max", list.size() - 1);
        return "user/userStore";
    }

    @PostMapping(value = "changeUsers")
    public String changeUsers(@RequestParam("button") String button){
        List<PersonalData> list = userDao.getAllUsers();
        if (button.equals("Delete " + 0)){
            return "redirect:/userStore";
        }
        for (int i = 1; i < list.size(); i++) {
            if (button.equals("Delete " + i)) {
                int id = list.get(i).getId();
                userDao.deleteUser(id);
                return "redirect:/userStore";
            }
        } return "redirect:/userStore";
    }
}
