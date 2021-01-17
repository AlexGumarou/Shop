package controller;

import entity.Goods;
import entity.Order;
import entity.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.GoodsService;
import service.ShopService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShopControllers {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "mainWindowShop")
    public String mainWindowShop(HttpSession session){
        List<Goods> list = goodsService.getAllGoods()
                .stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
        session.setAttribute("listGoods", list);
        return "shop/mainWindowStore";
    }

    @PostMapping(value = "mainWindowShop")
    public String mainWindowShopPost(@RequestParam(value = "qua", defaultValue = "0") int quantity,
                                     @RequestParam(value = "button", defaultValue = "0") int button, Model model){
        List<Goods> list = goodsService.getAllGoods();
        List<Order> listOrder = shopService.getOneOrder();
        try {
            for (Goods goods : list) {
                int id = goods.getId();
                String name = goods.getName();
                int price = goods.getPrice();
                if (quantity > 0) {
                    if (button == id) {
                        for (Order order : listOrder) {
                            if (goods.getName().equals(order.getName())) {
                                quantity = quantity + order.getQuantity();
                                if (quantity > goods.getQuantity()) {
                                    quantity = goods.getQuantity();
                                    model.addAttribute("msg", "We have only " +
                                            goods.getQuantity() + " of " +
                                            goods.getName() + ". " +
                                            "We have to add max we have. <br>");
                                }
                                int idOrder = order.getId();
                                int sum = quantity * order.getPrice();
                                shopService.editOneOrder(idOrder, quantity, sum);
                                return "shop/mainWindowStore";
                            }
                        }
                    }
                    if (button == id) {
                        if (quantity > goods.getQuantity()) {
                            model.addAttribute("msg", "We have only " +
                                    goods.getQuantity() + " of " +
                                    goods.getName() + ". " +
                                    "We have to add max we have. <br>");
                            quantity = goods.getQuantity();
                            int sum = quantity * price;
                            shopService.addOneOrder(name, price, quantity, sum);
                        } else {
                            int sum = quantity * price;
                            shopService.addOneOrder(name, price, quantity, sum);
                        }
                    }
                } else {
                    return "shop/mainWindowStore";
                }
            }
            return "shop/mainWindowStore";
        } catch (IllegalArgumentException e){
            return "shop/mainWindowStore";
        }
    }

    @RequestMapping(value = "basket", method = {RequestMethod.POST, RequestMethod.GET})
    public String basket(Model model){
        List<Order> list = shopService.getOneOrder().stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
        model.addAttribute("listOrder", list);
        int sum = list.stream().mapToInt(Order::getSum).sum();
        model.addAttribute("sum",sum);
        return "shop/basket";
    }

    @PostMapping(value = "choice")
    public String choice(HttpSession session,
                         @RequestParam(value = "wayOf", defaultValue = "redirect:/basket") String wayOf){
        if (wayOf.equals("pickUp") || wayOf.equals("delivery")) {
            String user = session.getAttribute("login").toString();
            List<Order> list = shopService.getOneOrder().stream().filter(s->s.getQuantity()>0).collect(Collectors.toList());
            shopService.addOrders(user,list
                    .toString().replace("{", "").replace("}", "")
                    .replace(",", "").replace("[", "").replace("]", ""));
            List<Order> listOrder = shopService.getOneOrder();
            List<Goods> listGoods = goodsService.getAllGoods();
            for (Order order : listOrder) {
                int quantity = order.getQuantity();
                String name = order.getName();
                for (Goods goods : listGoods) {
                    if (goods.getName().equals(name)) {
                        int id = goods.getId();
                        int result = goods.getQuantity() - quantity;
                        goodsService.changeGoods(id, result);
                    }
                }
            }
            shopService.deleteOneOrder();
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
    public String editBasketGet(Model model, @RequestParam(value = "button", defaultValue = "0") int button){
        List<Order> list = shopService.getOneOrder();
        for (Order order : list){
            if (order.getId() == button){
                int id = order.getId();
                String name = order.getName();
                int price = order.getPrice();
                int quantity = order.getQuantity();
                model.addAttribute("id", id);
                model.addAttribute("name", name);
                model.addAttribute("price", price);
                model.addAttribute("quantity", quantity);
            }
        }
        return "shop/editBasket";
    }

    @PostMapping(value = "editBasket")
    public String editBasket(@RequestParam(value = "quantity", defaultValue = "0") int quantity,
                             @ModelAttribute("name") String name){
        List<Order> list = shopService.getOneOrder();
        List<Goods> listGoods = goodsService.getAllGoods();
        int maxQuantity = 0;
        for (Order order : list){
            if (order.getName().equals(name)) {
                for (Goods goods : listGoods) {
                    if (goods.getName().equals(name)) {
                        maxQuantity = goods.getQuantity();
                    }
                }
                if (quantity >= 0) {
                    if (quantity < maxQuantity) {
                        shopService.editOneOrder(order.getId(), quantity, quantity*order.getPrice());
                    } else if (quantity > maxQuantity) {
                        shopService.editOneOrder(order.getId(), maxQuantity, quantity*order.getPrice());
                    }
                }
            }
        }
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
        List<PersonalData> list = userService.getAllUsers();
        String name =  session.getAttribute("nameUser").toString();
        for (PersonalData personalData : list) {
            if (personalData.getName().equals(name)) {
                int id = personalData.getId();
                userService.setAdditionalFields(id,address,email,phone);
            }
        } return "shop/deliveryFinal";
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
