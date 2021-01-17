package controller;

import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GoodsService;

import java.util.List;
@Controller
public class GoodsControllers {
    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "addGoods")
    public String addGoods(){
        return "goods/addGoods";
    }

    @PostMapping(value = "addGoods")
    public String addGoodsPost(@RequestParam(value = "quantity", defaultValue = "0") String quantity,
                               @RequestParam(value = "price", defaultValue = "0") String price,
                               @RequestParam(value = "name", defaultValue = "") String name,
                               @RequestParam(value = "description", defaultValue = "") String description){
        if (goodsService.addGoods(name,description,quantity,price)){
            return "redirect:/modifyGoods";
        } else {
            return "goods/incorrectAdd";
        }
    }
    @RequestMapping(value = "modifyGoods", method = {RequestMethod.POST, RequestMethod.GET})
    public String modifyGoods(Model model){
        List<Goods> list = goodsService.getAllGoods();
        model.addAttribute("goods", list);
        model.addAttribute("max", list.size() - 1);
        return "goods/modifyGoods";
    }

    @PostMapping(value = "changeGoods")
    public String changeGoods(@RequestParam(value = "button") String button){
        goodsService.deleteGoods(button);
        return "redirect:/modifyGoods";
    }

    @GetMapping(value = "editGoods")
    public String editGoods(@RequestParam(value = "button") String button, Model model){
        List<Goods> list = goodsService.getAllGoods();
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
    public String editGoodsPost(@RequestParam(value = "id", defaultValue = "0") String id,
                                @RequestParam(value = "price", defaultValue = "0") String price,
                                @RequestParam(value = "quantity", defaultValue = "0") String quantity,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "description") String description){
        if (goodsService.editGoods(id,name,description,quantity,price)){
            return "redirect:/modifyGoods";
        } else {
            return "goods/incorrectAdd";
        }
    }
}
