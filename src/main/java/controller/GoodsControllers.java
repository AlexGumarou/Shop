package controller;

import dao.GoodsDAO;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.IllegalFormatException;
import java.util.List;
//не работает try-catch при вводе буквы вместо цифры (addGoods, editGoods). (также в mainWindowShop)
// В EditGoods при пустых полях добавляет вместо ошибки - дефолтовые значения
@Controller
public class GoodsControllers {
    @Autowired
    GoodsDAO goodsDao;

    @GetMapping(value = "addGoods")
    public String addGoods(){
        return "goods/addGoods";
    }

    @PostMapping(value = "addGoods")
    public String addGoodsPost(@RequestParam(value = "quantity", defaultValue = "0") int quantity,
                               @RequestParam(value = "price", defaultValue = "0") int price,
                               @RequestParam(value = "name", defaultValue = "") String name,
                               @RequestParam(value = "description", defaultValue = "") String description){
        try {
            if (quantity > 0 && price > 0 && !name.trim().equals("")
                    && !description.trim().equals("")) {
                goodsDao.addGoods(name,description,quantity,price);
                return "redirect:/modifyGoods";
            } else {
                return "goods/incorrectAdd";
            }
        } catch (NumberFormatException | IllegalFormatException e){
            return "goods/incorrectAdd";
        }
    }
    @RequestMapping(value = "modifyGoods", method = {RequestMethod.POST, RequestMethod.GET})
    public String modifyGoods(Model model){
        List<Goods> list = goodsDao.getAllGoods();
        model.addAttribute("goods", list);
        model.addAttribute("max", list.size() - 1);
        return "goods/modifyGoods";
    }

    @PostMapping(value = "changeGoods")
    public String changeGoods(@RequestParam(value = "button") String button){
        List<Goods> list = goodsDao.getAllGoods();
        for (int i = 0; i< list.size(); i++) {
            int id = list.get(i).getId();
            if (button.equals("Delete " + i)) {
                goodsDao.deleteGoods(id);
            }
        } return "redirect:/modifyGoods";
    }

    @GetMapping(value = "editGoods")
    public String editGoods(@RequestParam(value = "button") String button, Model model){
        List<Goods> list = goodsDao.getAllGoods();
        for (int i = 0; i< list.size(); i++) {
            if (button.equals("Edit " + i)){
                model.addAttribute("id",list.get(i).getId());
                model.addAttribute("name",list.get(i).getName());
                model.addAttribute("description",list.get(i).getDescription());
                model.addAttribute("quantity",list.get(i).getQuantity());
                model.addAttribute("price",list.get(i).getPrice());
            }
        } return "goods/editGoods";
    }
    @PostMapping(value = "editGoods")
    public String editGoodsPost(@RequestParam(value = "id", defaultValue = "0") int id,
                                @RequestParam(value = "price", defaultValue = "0") int price,
                                @RequestParam(value = "quantity", defaultValue = "0") int quantity,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "description") String description){
        List<Goods> list = goodsDao.getAllGoods();
        if (id > 0 || price > 0 || quantity > 0){
            for (Goods goods : list) {
                if (goods.getId() == id) {
                    goodsDao.editGoods(id, name, description, quantity, price);
                }
            }
        } else {
            return "goods/incorrectAdd";
        } return "redirect:/modifyGoods";
    }
}
