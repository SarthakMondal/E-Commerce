package com.example.backend.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="activeorders_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@Component
public class MyOrdersModel
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "ordered_products")
    private String orderedProducts;

    @Column(name = "orderid_orders")
    private String orderIdforOrder;

    @Column(name = "order_time")
    private Date timeOfOrder;

    @Column(name = "order_price")
    private int orderAmount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(String orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getOrderIdforOrder() {
        return orderIdforOrder;
    }

    public void setOrderIdforOrder(String orderIdforOrder) {
        this.orderIdforOrder = orderIdforOrder;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }
}
