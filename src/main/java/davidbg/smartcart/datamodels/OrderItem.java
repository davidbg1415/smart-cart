package davidbg.smartcart.datamodels;

/**
 * מחלקה המייצגת פריט בודד בתוך הזמנה כצילום מצב (Snapshot).
 * מחלקה זו קריטית למערכת מאחר והיא שומרת את פרטי המוצר והציון שהאלגוריתם העניק לו
 * בדיוק ברגע הרכישה, ללא קשר לשינויים עתידיים במלאי או בהעדפות המשתמש.
 */
public class OrderItem 
{
    // מזהה הייחודי של המוצר המקורי במסד הנתונים
    private String productId;

    // שם המוצר כפי שהופיע בעת ביצוע ההזמנה
    private String productName;

    // מחיר המוצר בעת הרכישה (מיוצג ביחידות של 10 אגורות)
    private int price;

    // כתובת ה-URL של תמונת המוצר לצורך תצוגה בהיסטוריה
    private String imageUrl;

    // הציון (Score) שהאלגוריתם החכם העניק לפריט זה עבור המשתמש הספציפי ברגע הרכישה
    private double matchScore;

    /**
     * בנאי ברירת מחדל (Default Constructor).
     * נדרש עבור פעולות הסריאליזציה של Spring Data MongoDB.
     */
    public OrderItem() 
    {
    }

    /**
     * מחזירה את המזהה של המוצר המקורי.
     * @return מזהה המוצר כמחרוזת.
     */
    public String getProductId() 
    { 
        return productId; 
    }

    /**
     * מעדכנת את מזהה המוצר עבור פריט ההזמנה.
     * @param productId מזהה המוצר להגדרה.
     */
    public void setProductId(String productId) 
    { 
        this.productId = productId; 
    }

    /**
     * מחזירה את שם המוצר שנשמר ב-Snapshot.
     * @return שם המוצר.
     */
    public String getProductName() 
    { 
        return productName; 
    }

    /**
     * מעדכנת את שם המוצר בתוך פריט ההזמנה.
     * @param productName שם המוצר לשמירה.
     */
    public void setProductName(String productName) 
    { 
        this.productName = productName; 
    }

    /**
     * מחזירה את מחיר הפריט כפי שהיה בעת הרכישה.
     * @return מחיר ביחידות של 10 אגורות.
     */
    public int getPrice() 
    { 
        return price; 
    }

    /**
     * מעדכנת את מחיר הפריט עבור ההזמנה.
     * @param price מחיר לעדכון.
     */
    public void setPrice(int price) 
    { 
        this.price = price; 
    }

    /**
     * מחזירה את כתובת התמונה של המוצר.
     * @return נתיב התמונה (URL).
     */
    public String getImageUrl() 
    { 
        return imageUrl; 
    }

    /**
     * מעדכנת את כתובת התמונה עבור פריט ההזמנה.
     * @param imageUrl כתובת התמונה להגדרה.
     */
    public void setImageUrl(String imageUrl) 
    { 
        this.imageUrl = imageUrl; 
    }

    /**
     * מחזירה את ציון ההתאמה (Match Score) שהאלגוריתם חישב עבור פריט זה.
     * שדה זה מהווה את לב הפרויקט ומתעד את רמת הרלוונטיות של הבגד למשתמש.
     * @return ציון ההתאמה המקורי ברגע הרכישה.
     */
    public double getMatchScore() 
    { 
        return matchScore; 
    }

    /**
     * שומרת את ציון ההתאמה שחושב על ידי האלגוריתם כ-Snapshot בתוך ההזמנה.
     * @param matchScore ציון ההתאמה שחושב.
     */
    public void setMatchScore(double matchScore) 
    { 
        this.matchScore = matchScore; 
    }
}