package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DAVID BEN GIGI
 */
@Document(collection = "Orders")
public class Order 
{
    @Id
    private String id;
    private String userId; // Reference למשתמש
    private LocalDateTime orderDate;
    private double totalPrice;
    private Address shippingAddress;
    private List<OrderItem> items; // Embedding של פריטי ההזמנה

    public Order() {}

    public String getId() 
    { 
        return id; 
    }

    public void setId(String id) 
    { 
        this.id = id; 
    }

    public String getUserId() 
    { 
        return userId; 
    }

    public void setUserId(String userId) 
    { 
        this.userId = userId; 
    }

    public LocalDateTime getOrderDate() 
    { 
        return orderDate; 
    }

    public void setOrderDate(LocalDateTime orderDate) 
    { 
        this.orderDate = orderDate; 
    }

    public double getTotalPrice() 
    { 
        return totalPrice; 
    }

    public void setTotalPrice(double totalPrice) 
    { 
        this.totalPrice = totalPrice; 
    }

    public Address getShippingAddress() 
    { 
        return shippingAddress; 
    }

    public void setShippingAddress(Address shippingAddress) 
    { 
        this.shippingAddress = shippingAddress; 
    }

    public List<OrderItem> getItems() 
    { 
        return items; 
    }

    public void setItems(List<OrderItem> items) 
    { 
        this.items = items; 
    }
}