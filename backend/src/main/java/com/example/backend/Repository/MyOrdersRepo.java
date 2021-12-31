package com.example.backend.Repository;
import com.example.backend.Model.MyOrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MyOrdersRepo extends JpaRepository<MyOrdersModel, Long>
{
    @Query(value = "SELECT * FROM `activeorders_info` WHERE `user_id` = :userId", nativeQuery = true)
    List<MyOrdersModel> findMyOrders(long userId);
}
