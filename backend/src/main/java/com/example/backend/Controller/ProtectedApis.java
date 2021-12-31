package com.example.backend.Controller;
import com.example.backend.Model.*;
import com.example.backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/protected")
@CrossOrigin(origins = "http://localhost:3000")
public class ProtectedApis
{
    @Autowired
    UserService backendService1;

    @Autowired
    ProductService backendService2;

    @Autowired
    CartService backendService3;

    @Autowired
    MyOrdersService backendService4;

    @GetMapping(path = "/hello")
    public String helloBackend(){return "E-Commerce Working";}

    @GetMapping(path = "/profile/{uId}")
    public UserModel profilePage(@PathVariable String uId)
    {
        return this.backendService1.getMyProfile(Long.parseLong(uId));
    }

    @GetMapping(path = "/getmyidandrole/{emailId}")
    public String myRoleAndId(@PathVariable String emailId)
    {
        return this.backendService1.findMyUserIdandRole(emailId);
    }

    @PutMapping(path = "/updateuser/{uId}")
    public String updateUserData(@PathVariable String uId, @RequestBody UserModel user)
    {
        boolean ret = this.backendService1.updateUser(Long.parseLong(uId), user);

        if(ret==true)
        {
            return "Successful";
        }

        else
        {
            return "Failed";
        }
    }



    @PostMapping(path = "/addtocart")
    public String addItemtoCart(@RequestBody CartModel cart)
    {
        boolean ret = this.backendService3.addToCart(cart);

        if(ret == true)
        {
            return "Successful";
        }

        else if(ret == false)
        {
            return "Already Exists";
        }

        else
        {
            return "Failed";
        }
    }

    @GetMapping(path = "/mycartitems/{uId}")
    public List<List<String>> viewMyCart(@PathVariable String uId)
    {
        return this.backendService3.showProductsInCart(Long.parseLong(uId));
    }

    @PutMapping(path = "/changeqtnatcart")
    public String updateQtnInCart(@RequestBody CartModel cart)
    {
        boolean ret = this.backendService3.changeProductQtn(cart);

        if(ret==true)
        {
            return "Successful";
        }

        else
        {
            return "Failed";
        }
    }

    @DeleteMapping(path = "/removeitemfromcart/{uId}/{pId}")
    public String removeItemFromCart(@PathVariable String uId, @PathVariable String pId)
    {
        boolean ret = this.backendService3.removeItemFromCart(Long.parseLong(uId), Long.parseLong(pId));

        if(ret==true)
        {
            return "Successful";
        }

        else
        {
            return "Failed";
        }
    }



    @GetMapping(path = "/getprice/{uId}")
    public String getPrice(@PathVariable  String uId)
    {
        return this.backendService4.getPriceDetails(Long.parseLong(uId));
    }

    @PostMapping(path = "/placemyorder/{uId}")
    public String placeOrder(@PathVariable String uId)
    {
        boolean ret = this.backendService4.placeOrder(Long.parseLong(uId));

        if(ret==true)
        {
            return "Successful";
        }

        else if(ret == false)
        {
            return "Empty Cart";
        }

        else
        {
            return "Failed";
        }
    }

    @GetMapping(path = "/getmyorders/{uId}")
    public List<MyOrdersModel> getMyAllOrders(@PathVariable String uId)
    {
        return this.backendService4.getMyOrders(Long.parseLong(uId));
    }
}
