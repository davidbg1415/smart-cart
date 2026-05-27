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
 * שכבת השירות (Service Layer) לניהול ההזמנות במערכת SmartCart.
 * מחלקה זו מרכזת את כל הלוגיקה העסקית הקשורה לתהליך הרכישה, החל מיצירת הזמנה חדשה 
 * מתוך סל הקניות ועד לעיבוד נתונים סטטיסטיים מתקדמים עבור מנהל המערכת.
 * המטרה היא להשאיר את שכבת הממשק (UI) "רזה" ככל האפשר ולבצע את כל העיבודים 
 * בתוך שכבת ה-Service.
 * @author DAVID BEN GIGI
 */
@Service
public class OrderService 
{
    private OrderRepository orderRepository;

    /**
     * בנאי המאתחל את ה-Repository הדרוש לתקשורת עם מסד הנתונים.
     * @param orderRepository אובייקט הגישה לנתוני ההזמנות.
     */
    public OrderService(OrderRepository orderRepository)
    {
       this.orderRepository = orderRepository;
    }

    /**
     * שמירת הזמנה חדשה במסד הנתונים.
     * @param order אובייקט ההזמנה להוספה.
     */
    public void addOrderToDB(Order order)
    {
       orderRepository.insert(order);
    }

    /**
     * שליפת כל ההזמנות שבוצעו על ידי משתמש ספציפי.
     * @param userId מזהה המשתמש.
     * @return רשימת הזמנות המקושרות למשתמש.
     */
    public ArrayList<Order> getOrdersByUserId(String userId)
    {
       return (ArrayList<Order>)orderRepository.findByUserId(userId);
    }

    /**
     * מחיקת הזמנה מתוך מסד הנתונים לפי מזהה.
     * @param id מזהה ההזמנה למחיקה.
     */
    public void deleteOrder(String id)
    {
       orderRepository.deleteById(id);
    }

    /**
     * שליפת כל ההזמנות הקיימות במערכת (לשימוש בלוח הבקרה של המנהל).
     * @return רשימת כל ההזמנות.
     */
    public List<Order> getAllOrders()
    {
       return orderRepository.findAll();
    }

    /**
     * ממירה עגלת קניות (רשימת מוצרים) לאובייקט הזמנה שלם.
     * שיטה זו מיישמת את ה-Snapshot Pattern: היא מעתיקה את פרטי המוצרים (שם, מחיר, תמונה) 
     * כפי שהיו ברגע הרכישה לתוך מחלקת OrderItem, כדי להבטיח עקביות היסטורית.
     * @param userId מזהה המשתמש הרוכש.
     * @param cartProducts רשימת המוצרים שנבחרו בסל.
     * @return אובייקט Order מוכן לשמירה.
     */
    public Order createOrderFromCart(String userId, List<Product> cartProducts) 
    {
        Order newOrder = new Order();
        newOrder.setUserId(userId);
        newOrder.setOrderDate(LocalDateTime.now());
        
        // חישוב סכום סופי על בסיס מחירי המוצרים (חלוקה ב-10.0 להמרה מאגורות לשקלים)
        double total = cartProducts.stream().mapToDouble(p -> p.getPrice() / 10.0).sum();
        newOrder.setTotalPrice(total);

        // המרת מוצרים ל-OrderItem ליצירת "צילום מצב" (Snapshot) של העסקה
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
     * מחשבת את סך ההכנסות הכללי של המערכת מכל ההזמנות שבוצעו.
     * משתמשת ב-Java Stream API לחישוב יעיל ומרוכז.
     * @return סכום ההכנסות.
     */
    public double calculateTotalRevenue() 
    {
        // שימוש במפה-ל-Double כדי לצבור את סך המחירים של כל ההזמנות
        return getAllOrders().stream().mapToDouble(Order::getTotalPrice).sum();
    }

    /**
     * מפיקה דוח הכנסות חודשי המקובץ לפי תאריכים ("MM/yyyy").
     * מאפשר למנהל המערכת לראות את מגמות המכירות לאורך זמן.
     * @return מפה הממפה תאריך לסך הרווחים באותו חודש.
     */
    public Map<String, Double> getMonthlyRevenue() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return getAllOrders().stream()
            .filter(o -> o.getOrderDate() != null) // סינון הזמנות תקינות בעלות תאריך
            .collect(Collectors.groupingBy(
                o -> o.getOrderDate().format(formatter), // יצירת מפתח מפורמט
                Collectors.summingDouble(Order::getTotalPrice) // חישוב סכום מצטבר לכל חודש
            ));
    }

    /**
     * סופרת את סך כל הפריטים שנמכרו אי פעם בכל ההזמנות.
     * @return כמות הפריטים הכוללת.
     */
    public long getTotalItemsSold() 
    {
        return getAllOrders().stream()
                .filter(o -> o.getItems() != null) // הגנה מול הזמנות ריקות
                .mapToLong(o -> o.getItems().size()) // סכימת כמות הפריטים בכל הזמנה
                .sum();
    }

    /**
     * מבצעת ניתוח פופולריות מוצרים.
     * מחזירה מפה המציגה את שם המוצר וכמות הפעמים שהוא הופיע בהזמנות.
     * @return מפה של מוצרים ומספר המכירות שלהם.
     */
    public Map<String, Long> getProductPopularity() 
    {
        return getAllOrders().stream()
                .filter(o -> o.getItems() != null)
                .flatMap(o -> o.getItems().stream()) // "שטוח" את רשימות הפריטים לרצף אחד ארוך
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.counting())); // קיבוץ לפי שם המוצר וספירה
    }

    /**
     * מאתרת ומחזירה את המוצר הנמכר ביותר במערכת.
     * במידה ואין הזמנות, מחזירה הודעת חיווי מתאימה.
     * @return שם המוצר המוביל במכירות.
     */
    public String getTopSellingProduct() 
    {
        Map<String, Long> productCounts = getProductPopularity();
        // חיפוש הערך המקסימלי מתוך המפה שהופקה
        return productCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey) // שליפת שם המוצר המנצח
                .orElse("אין נתונים עדיין"); // טיפול במקרה של מפה ריקה
    }
}