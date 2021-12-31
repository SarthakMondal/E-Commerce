package com.example.backend.Service;

import com.example.backend.Model.CartModel;
import com.example.backend.Model.ProductModel;
import com.example.backend.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{
    @Autowired
    CartRepo backEnd;

    @Autowired
    ProductService backendService;

    public boolean addToCart(CartModel cart)
    {
        int n = this.backEnd.ifProductAlreadyInCart(cart.getUserId(), cart.getProductId());

        if(n==0)
        {
            this.backEnd.save(cart);
            return true;
        }

        else
        {
            return false;
        }
    }

    public List<List<String>> showProductsInCart(long userId)
    {
       List<List<String>> ret = new ArrayList<List<String>>();
        List<CartModel> cart = this.backEnd.showMyCart(userId);
        int i=0,n=cart.size();

        if(n!=0)
        {
            for(i=0;i<n;i++)
            {
                List<String> a = new ArrayList<String>();
                a.add(this.backendService.getProductById(cart.get(i).getProductId()).getProductImage());
                a.add(this.backendService.getProductById(cart.get(i).getProductId()).getProductName());
                a.add(String.valueOf(this.backendService.getProductById(cart.get(i).getProductId()).getProductPrice()));
                a.add(String.valueOf(cart.get(i).getProductQuantity()));
                a.add(String.valueOf(cart.get(i).getProductId()));
                ret.add(a);
            }
        }

        return ret;
    }

    public boolean changeProductQtn(CartModel cart)
    {
        this.backEnd.updateMyCart(cart.getUserId(), cart.getProductId(), cart.getProductQuantity());
        return true;
    }

    public boolean removeItemFromCart(long userId, long productId)
    {
        this.backEnd.removeItemsFromCart(userId, productId);
        return true;
    }

    public boolean emptyCart(long userId)
    {
        this.backEnd.checkoutOrder(userId);
        return true;
    }
}
