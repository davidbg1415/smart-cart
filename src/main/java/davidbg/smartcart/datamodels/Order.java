package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

/**
 * הסבר על המחלקה:
 * מחלקה זו מייצגת הזמנה מלאה במערכת. 
 * היא משמשת לשמירת היסטוריית הרכישות וקישור בין משתמש למוצרים.
 * * @author DAVID BEN GIGI
 */
@Document(collection = "Orders")
public class Order 
{
    @Id
    private String id;              // מזהה ייחודי של ההזמנה במסד הנתונים
    private String userId;          // מזהה המשתמש שביצע את ההזמנה
    private LocalDateTime orderDate; // תאריך ושעת ביצוע ההזמנה
    private double totalPrice;      // המחיר הכולל של העסקה
    private Address shippingAddress; // כתובת היעד למשלוח החבילה
    private List<OrderItem> items;   // רשימה של פריטי הלבוש שנרכשו

    /**
     * בנאי ברירת מחדל:
     * נדרש עבור ספריות המערכת לצורך יצירת אובייקט ריק.
     */
    public Order() {}

    /**
     * בנאי מאתחל עם פרמטרים:
     * מאפשר יצירת הזמנה חדשה ומילוי כל הנתונים בשורה אחת.
     * * @param id מזהה ההזמנה.
     * @param userId מזהה המשתמש.
     * @param orderDate זמן הרכישה.
     * @param totalPrice סכום כולל.
     * @param shippingAddress כתובת למשלוח.
     * @param items רשימת פריטים.
     */
    public Order(String id, String userId, LocalDateTime orderDate, double totalPrice, Address shippingAddress, List<OrderItem> items) 
    {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    /**
     * מחזירה את המזהה הייחודי של ההזמנה.
     * @return מחרוזת המייצגת את מפתח ההזמנה.
     */
    public String getId() 
    { 
        return id; 
    }

    /**
     * מעדכנת את המזהה הייחודי של ההזמנה.
     * @param id מזהה חדש להגדרה.
     */
    public void setId(String id) 
    { 
        this.id = id; 
    }

    /**
     * מחזירה את המזהה של המשתמש בעל ההזמנה.
     * @return מחרוזת המייצגת את מזהה המשתמש.
     */
    public String getUserId() 
    { 
        return userId; 
    }

    /**
     * מקשרת את ההזמנה למשתמש ספציפי.
     * @param userId מזהה המשתמש לקישור.
     */
    public void setUserId(String userId) 
    { 
        this.userId = userId; 
    }

    /**
     * מחזירה את זמן ביצוע ההזמנה.
     * @return אובייקט זמן המכיל תאריך ושעה.
     */
    public LocalDateTime getOrderDate() 
    { 
        return orderDate; 
    }

    /**
     * מעדכנת את זמן ביצוע ההזמנה.
     * @param orderDate ערך זמן חדש להגדרה.
     */
    public void setOrderDate(LocalDateTime orderDate) 
    { 
        this.orderDate = orderDate; 
    }

    /**
     * מחזירה את המחיר הסופי של ההזמנה.
     * @return ערך מספרי המייצג את המחיר.
     */
    public double getTotalPrice() 
    { 
        return totalPrice; 
    }

    /**
     * מעדכנת את המחיר הכולל של ההזמנה.
     * @param totalPrice סכום כספי חדש לעדכון.
     */
    public void setTotalPrice(double totalPrice) 
    { 
        this.totalPrice = totalPrice; 
    }

    /**
     * מחזירה את כתובת המשלוח המקושרת.
     * @return אובייקט המכיל את פרטי הכתובת.
     */
    public Address getShippingAddress() 
    { 
        return shippingAddress; 
    }

    /**
     * מעדכנת את כתובת היעד למשלוח.
     * @param shippingAddress אובייקט כתובת חדש.
     */
    public void setShippingAddress(Address shippingAddress) 
    { 
        this.shippingAddress = shippingAddress; 
    }

    /**
     * מחזירה את רשימת הפריטים המופיעים בהזמנה.
     * @return רשימה המכילה את נתוני המוצרים.
     */
    public List<OrderItem> getItems() 
    { 
        return items; 
    }

    /**
     * מעדכנת את רשימת הפריטים בהזמנה.
     * @param items רשימת מוצרים חדשה לעדכון.
     */
    public void setItems(List<OrderItem> items) 
    { 
        this.items = items; 
    }
}