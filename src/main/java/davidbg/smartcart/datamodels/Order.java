package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

/**
 * מחלקה המייצגת הזמנה מלאה במערכת SmartCart.
 * מחלקה זו משמשת למיפוי מסמכים ב-Collection "Orders" במסד הנתונים MongoDB.
 * היא מתעדת את היסטוריית הרכישות ומקשרת בין משתמשים למוצרים שנרכשו.
 * * @author DAVID BEN GIGI
 */
@Document(collection = "Orders")
public class Order 
{
    @Id
    private String id;               // מזהה ייחודי של ההזמנה (Primary Key) במסד הנתונים
    
    private String userId;           // מזהה המשתמש (User ID) שביצע את ההזמנה לקישור בין טבלאות
    
    private LocalDateTime orderDate; // אובייקט המתעד את התאריך והשעה המדויקים של ביצוע הרכישה
    
    private double totalPrice;       // המחיר הסופי והכולל של כל פריטי ההזמנה
    
    private List<OrderItem> items;   // רשימה מפורטת של פריטי הלבוש (שם, תמונה ומחיר) שנכללו בעסקה

    /**
     * בנאי ברירת מחדל (Default Constructor).
     * נדרש על ידי Spring Data ו-Jackson לצורך יצירת אובייקט ריק וביצוע דה-סריאליזציה.
     */
    public Order() 
    {
    }

    /**
     * בנאי מאתחל עם פרמטרים (Parameterized Constructor).
     * מאפשר יצירת אובייקט הזמנה מלא ומילוי כל נתוניו בעת השמירה למסד הנתונים.
     * * @param id מזהה ההזמנה
     * @param userId מזהה המשתמש הרוכש
     * @param orderDate מועד ביצוע ההזמנה
     * @param totalPrice הסכום הכולל לתשלום
     * @param shippingAddress כתובת למשלוח
     * @param items רשימת הפריטים שנרכשו
     */
    public Order(String id, String userId, LocalDateTime orderDate, double totalPrice, List<OrderItem> items) 
    {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    /**
     * מחזירה את המזהה הייחודי של ההזמנה.
     * @return מחרוזת המייצגת את מפתח המסמך.
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
     * מחזירה את המזהה של המשתמש שביצע את ההזמנה.
     * @return מזהה המשתמש לצורך שיוך ההיסטוריה.
     */
    public String getUserId() 
    { 
        return userId; 
    }

    /**
     * מקשרת את ההזמנה למשתמש ספציפי במערכת.
     * @param userId מזהה המשתמש לקישור.
     */
    public void setUserId(String userId) 
    { 
        this.userId = userId; 
    }

    /**
     * מחזירה את זמן ביצוע ההזמנה מהמסד.
     * @return אובייקט זמן המכיל תאריך ושעה מדויקים.
     */
    public LocalDateTime getOrderDate() 
    { 
        return orderDate; 
    }

    /**
     * מעדכנת את זמן ביצוע ההזמנה.
     * @param orderDate ערך זמן חדש (בדרך כלל זמן נוכחי בעת הרכישה).
     */
    public void setOrderDate(LocalDateTime orderDate) 
    { 
        this.orderDate = orderDate; 
    }

    /**
     * מחזירה את המחיר הסופי של ההזמנה לאחר חישוב.
     * @return ערך מספרי המייצג את הסכום הכולל.
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
     * מחזירה את רשימת הפריטים המופיעים בתוך ההזמנה.
     * @return רשימה של אובייקטי OrderItem המכילים את פרטי המוצרים שנרכשו.
     */
    public List<OrderItem> getItems() 
    { 
        return items; 
    }

    /**
     * מעדכנת את רשימת הפריטים הכלולים בהזמנה.
     * @param items רשימת מוצרים חדשה לעדכון.
     */
    public void setItems(List<OrderItem> items) 
    { 
        this.items = items; 
    }
}