package com.example.backend.Service;
import com.example.backend.Model.ProductModel;
import com.example.backend.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    ProductRepo backEnd;

    public List<ProductModel> getAllProducts()
    {
        return this.backEnd.findAll();
    }

    public List<ProductModel> searchProduct(String phrase)
    {
        if(!isNumeric(phrase))
        {
            return this.backEnd.searchProductsByNameOrType(phrase);
        }

        else
        {
            return this.backEnd.searchProductsUnderCost(phrase);
        }
    }

    public ProductModel getProductById(long pId)
    {
        return this.backEnd.findById(pId).get();
    }

    private boolean isNumeric(String val)
    {
        try
        {
            Integer.parseInt(val);
            return true;
        }

        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
