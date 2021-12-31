package com.example.backend.Controller;

import com.example.backend.Model.OauthModel;
import com.example.backend.Model.ProductModel;
import com.example.backend.Model.UserModel;
import com.example.backend.Service.OauthLoginService;
import com.example.backend.Service.ProductService;
import com.example.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/unprotected")
@CrossOrigin(origins = "http://localhost:3000")
public class UnprotectedApis
{
    @Autowired
    OauthLoginService backendService1;

    @Autowired
    UserService backendService2;

    @Autowired
    ProductService backendService3;

    @PostMapping(path="/authandlogin")
    public String getUsers(@RequestBody OauthModel model)
    {
        String uname = model.getUsername();
        String psw = model.getPassword();
        return this.backendService1.Oauth2LoginService(uname, psw);
    }

    @GetMapping(path="/ifuserexists/{emailId}")
    public boolean ifUserRequiresSignup(@PathVariable String emailId)
    {
        return this.backendService2.ifSignupRequired(emailId);
    }
    @PostMapping(path = "/signup")
    public String resigterUser(@RequestBody UserModel user)
    {
        boolean ret = this.backendService2.addUser(user);

        if(ret==true)
        {
            return "Successful";
        }

        else
        {
            return "Failed";
        }
    }

    @GetMapping(path = "/getallproducts")
    public List<ProductModel> getAllProducts()
    {
        return this.backendService3.getAllProducts();
    }

    @GetMapping(path = "/searchproduct/{phrase}")
    public List<ProductModel> seacrchFromProducts(@PathVariable String phrase)
    {
        return this.backendService3.searchProduct(phrase);
    }

}
