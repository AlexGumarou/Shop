package controller;

import entity.OrderOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GoodsService;
import service.ShopService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopControllers {
    private GoodsService goodsService;
    private UserService userService;
    private ShopService shopService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(value = "mainWindowShop")
    public String mainWindowShop(HttpSession session){
        session.setAttribute("listGoods", goodsService.notNullAllGoods());
        return "shop/mainWindowStore";
    }

    @PostMapping(value = "mainWindowShop")
    public String mainWindowShopPost(@RequestParam(value = "qua", defaultValue = "0") String quantity,
                                     @RequestParam(value = "button", defaultValue = "0") String button, Model model){
        model.addAttribute("msg", shopService.mainShopPage(quantity,button));
        return "shop/mainWindowStore";
    }

    @RequestMapping(value = "basket", method = {RequestMethod.POST, RequestMethod.GET})
    public String basket(Model model){
        model.addAttribute("listOrder", shopService.listOrder());
        model.addAttribute("sum",shopService.count());
        return "shop/basket";
    }

    @PostMapping(value = "choice")
    public String choice(HttpSession session,
                         @RequestParam(value = "wayOf", defaultValue = "redirect:/basket") String wayOf){
        String user = session.getAttribute("login").toString();
        if (wayOf.equals("pickUp") || wayOf.equals("delivery")) {
            shopService.choice(user);
        } else {
            return "redirect:/basket";
        }
        if (wayOf.equals("pickUp")) {
            return "redirect:/selfDelivery";
        } else {
            return "redirect:/delivery";
        }
    }
    @GetMapping(value = "editBasket")
    public String editBasketGet(Model model, @RequestParam(value = "button", defaultValue = "0") String button){
        List<OrderOne> list = shopService.editBasket(button);
        model.addAttribute("id", list.get(0).getId());
        model.addAttribute("name", list.get(0).getName());
        model.addAttribute("price", list.get(0).getPrice());
        model.addAttribute("quantity", list.get(0).getQuantity());
        return "shop/editBasket";
    }

    @PostMapping(value = "editBasket")
    public String editBasket(@RequestParam(value = "quantity", defaultValue = "0") String quantity,
                             @ModelAttribute("name") String name){
        shopService.editBasket(quantity, name);
        return "redirect:/basket";
    }

    @GetMapping(value = "delivery")
    public String delivery(){
        return "shop/delivery";
    }

    @GetMapping(value = "selfDelivery")
    public String selfDelivery(){
        return "shop/selfDelivery";
    }

    @PostMapping(value = "deliveryFinal")
    public String deliveryFinal(@RequestParam("address") String address, @RequestParam("email") String email,
                                @RequestParam("phone") String phone, HttpSession session){
        String name =  session.getAttribute("nameUser").toString();
        userService.setAdditionalFields(name,address,email,phone);
        return "shop/deliveryFinal";
    }

    @GetMapping(value = "emptyOrder")
    public String emptyOrder(){
        return "shop/emptyOrder";
    }

    @PostMapping(value = "orderHistory")
    public String orderHistory(Model model){
        model.addAttribute("mapOrder", shopService.getAllOrders());
        return "shop/orderHistory";
    }
}
