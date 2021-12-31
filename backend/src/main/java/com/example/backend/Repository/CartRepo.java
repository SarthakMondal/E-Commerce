package com.example.backend.Repository;
import com.example.backend.Model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartModel, Long>
{
    @Query(value="SELECT * FROM `cart_info` WHERE `user_id` = :userId", nativeQuery = true)
    List<CartModel> showMyCart(long userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE `cart_info` SET `product_qtn` = :qtn WHERE `user_id`= :userId AND `product_id`= :productId", nativeQuery = true)
    void updateMyCart(long userId, long productId, int qtn);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM `cart_info` WHERE `user_id`= :userId AND `product_id`= :productId", nativeQuery=true)
    void removeItemsFromCart(long userId, long productId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM `cart_info` WHERE `user_id`= :userId", nativeQuery=true)
    void checkoutOrder(long userId);

    @Query(value="SELECT COUNT(*) FROM `cart_info` WHERE `user_id`= :userId AND `product_id`= :productId", nativeQuery=true)
    int ifProductAlreadyInCart(long userId, long productId);
}
