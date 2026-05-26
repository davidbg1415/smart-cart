package davidbg.smartcart.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.datamodels.OrderItem;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.repositories.OrderRepository;

/**
 * שכבת השירות עבור ניהול ההזמנות במערכת.
 * מכילה את הלוגיקה העסקית להמרת סל קניות להזמנה, ועיבוד נתונים פיננסיים.
 * @author DAVID BEN GIGI
 */
@Service
public class OrderService 
{
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
       this.orderRepository = orderRepository;
    }

    public void addOrderToDB(Order order)
    {
       orderRepository.insert(order);
    }

    public ArrayList<Order> getOrdersByUserId(String userId)
    {
       return (ArrayList<Order>)orderRepository.findByUserId(userId);
    }

    public void deleteOrder(String id)
    {
       orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders()
    {
       return orderRepository.findAll();
    }

    // ==========================================
    // מתודות לוגיקה עסקית שהועברו מה-UI לכאן!
    // ==========================================

    /**
     * ממירה עגלת קניות (רשימת מוצרים) לאובייקט הזמנה שלם כולל חישוב מחיר כולל.
     */
    public Order createOrderFromCart(String userId, List<Product> cartProducts) 
    {
        Order newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setOrderDate(LocalDateTime.now());
        
        // חישוב סכום סופי
        double total = cartProducts.stream().mapToDouble(p -> p.getPrice() / 10.0).sum();
        newOrder.setTotalPrice(total);

        // המרה ל-OrderItem
        List<OrderItem> orderItems = new ArrayList<>();
        for (Product p : cartProducts) 
        {
            OrderItem item = new OrderItem();
            item.setProductId(p.getId());
            item.setProductName(p.getName());
            item.setPrice(p.getPrice());
            item.setImageUrl(p.getImageUrl());
            orderItems.add(item);
        }
        newOrder.setItems(orderItems);
        
        return newOrder;
    }

    /**
     * מחשבת סך ההכנסות הכללי מכל ההזמנות.
     */
    public double calculateTotalRevenue() 
    {
        return getAllOrders().stream().mapToDouble(Order::getTotalPrice).sum();
    }

    /**
     * מחזירה מפה המקבצת את הרווחים לפי חודש ושנה (למשל "05/2026").
     */
    public Map<String, Double> getMonthlyRevenue() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return getAllOrders().stream()
            .filter(o -> o.getOrderDate() != null)
            .collect(Collectors.groupingBy(
                o -> o.getOrderDate().format(formatter),
                Collectors.summingDouble(Order::getTotalPrice)
            ));
    }

    /**
     * סופרת את סך כל הפריטים שנמכרו אי פעם באתר.
     */
    public long getTotalItemsSold() 
    {
        return getAllOrders().stream()
                .filter(o -> o.getItems() != null)
                .mapToLong(o -> o.getItems().size())
                .sum();
    }

    /**
     * מחזירה מפה של פופולריות מוצרים (שם המוצר וכמות המכירות).
     */
    public Map<String, Long> getProductPopularity() 
    {
        return getAllOrders().stream()
                .filter(o -> o.getItems() != null)
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.counting()));
    }

    /**
     * מאתרת ומחזירה את שם המוצר הנמכר ביותר.
     */
    public String getTopSellingProduct() 
    {
        Map<String, Long> productCounts = getProductPopularity();
        return productCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("אין נתונים עדיין");
    }
}