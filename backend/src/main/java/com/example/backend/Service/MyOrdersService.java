package com.example.backend.Service;
import com.example.backend.Model.MyOrdersModel;
import com.example.backend.Model.ProductModel;
import com.example.backend.Repository.MyOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@Service
public class MyOrdersService
{
    @Autowired
    CartService backendService;

    @Autowired
    MyOrdersRepo backEnd;

    public List<MyOrdersModel> getMyOrders(long userId)
    {
        return this.backEnd.findMyOrders(userId);
    }

    public boolean placeOrder(long userId)
    {
        MyOrdersModel order = new MyOrdersModel();
        boolean ret = true;

        List<List<String>> cartItems = this.backendService.showProductsInCart(userId);
        int i=0,n=cartItems.size(),total=0,orderPrice=0;

        if(n==0)
        {
            ret = false;
        }

        else
        {
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
            StringBuilder rand = new StringBuilder();

            for (i = 0; i < 10; i++)
            {
                int index = (int)(AlphaNumericString.length() * Math.random());
                rand.append(AlphaNumericString.charAt(index));
            }

            String orderIdForOrder = String.valueOf(rand);

            StringBuilder sb = new StringBuilder();
            for(i=0;i<n;i++)
            {
                if(i!=n-1)
                {
                    sb.append(cartItems.get(i).get(1));
                    sb.append(": " + cartItems.get(i).get(3));
                    sb.append(", ");
                }

                else
                {
                    sb.append(cartItems.get(i).get(1));
                    sb.append(": " + cartItems.get(i).get(3));
                }

                total+=Integer.parseInt(cartItems.get(i).get(2)) * Integer.parseInt(cartItems.get(i).get(3));
            }

            String productsName = String.valueOf(sb);
            this.backendService.emptyCart(userId);

            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            order.setOrderedProducts(productsName);
            order.setOrderIdforOrder(orderIdForOrder);
            order.setTimeOfOrder(timestamp);
            order.setUserId(userId);
            orderPrice = total + (total*12/100) + (total*18/100);
            order.setOrderAmount(orderPrice);
            this.backEnd.save(order);
            ret = true;
        }
        return ret;
    }

    public String getPriceDetails(long uId)
    {
        List<List<String>> cart = this.backendService.showProductsInCart(uId);
        int i=0,n=cart.size(),total=0;

        for(i=0;i<n;i++)
        {
            total+=Integer.parseInt(cart.get(i).get(2)) * Integer.parseInt(cart.get(i).get(3));
        }

        return String.valueOf(total)+","+String.valueOf((total*18/100))+","+String.valueOf((total*12/100));
    }


}
