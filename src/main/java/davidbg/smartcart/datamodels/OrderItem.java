package davidbg.smartcart.datamodels;

/**
 * הסבר על המחלקה:
 * מחלקה זו מייצגת פריט בודד בתוך הזמנה קיימת.
 * היא שומרת "צילום מצב" של נתוני המוצר ברגע הרכישה כדי להבטיח את אמינות הנתונים,
 * כולל הניקוד שחושב עבורו על ידי אלגוריתם התיעוד.
 * * @author DAVID BEN GIGI
 */
public class OrderItem 
{
    private String productId;     // מזהה ייחודי של המוצר המקורי
    private String nameSnapshot;  // שם המוצר כפי שהיה בזמן הרכישה
    private int priceSnapshot;    // מחיר המוצר בזמן הרכישה ביחידות של סנט
    private double calculatedScore; // הניקוד שחושב עבור פריט זה על ידי האלגוריתם

    /**
     * בנאי ברירת מחדל:
     * נדרש עבור ספריות המערכת לצורך יצירת אובייקט ריק.
     */
    public OrderItem() {}

    /**
     * בנאי מאתחל עם פרמטרים:
     * מאפשר יצירת פריט הזמנה מלא עם כל הנתונים הנדרשים בשורה אחת.
     * * @param productId מזהה המוצר.
     * @param nameSnapshot שם המוצר המעודכן.
     * @param priceSnapshot מחיר המוצר המעודכן.
     * @param calculatedScore הניקוד שחושב לפריט.
     */
    public OrderItem(String productId, String nameSnapshot, int priceSnapshot, double calculatedScore) 
    {
        this.productId = productId;
        this.nameSnapshot = nameSnapshot;
        this.priceSnapshot = priceSnapshot;
        this.calculatedScore = calculatedScore;
    }

    /**
     * מחזירה את המזהה של המוצר.
     * @return מחרוזת המייצגת את מפתח המוצר.
     */
    public String getProductId() 
    { 
        return productId; 
    }

    /**
     * מעדכנת את מזהה המוצר.
     * @param productId מזהה חדש לעדכון.
     */
    public void setProductId(String productId) 
    { 
        this.productId = productId; 
    }

    /**
     * מחזירה את שם המוצר כפי שנשמר בהזמנה.
     * @return מחרוזת של שם המוצר.
     */
    public String getNameSnapshot() 
    { 
        return nameSnapshot; 
    }

    /**
     * מעדכנת את שם המוצר עבור תיעוד ההזמנה.
     * @param nameSnapshot שם מוצר חדש לעדכון.
     */
    public void setNameSnapshot(String nameSnapshot) 
    { 
        this.nameSnapshot = nameSnapshot; 
    }

    /**
     * מחזירה את מחיר המוצר כפי שנשמר בהזמנה.
     * @return ערך מספרי של המחיר ביחידות קטנות.
     */
    public int getPriceSnapshot() 
    { 
        return priceSnapshot; 
    }

    /**
     * מעדכנת את מחיר המוצר עבור תיעוד ההזמנה.
     * @param priceSnapshot מחיר מוצר חדש לעדכון.
     */
    public void setPriceSnapshot(int priceSnapshot) 
    { 
        this.priceSnapshot = priceSnapshot; 
    }

    /**
     * מחזירה את הניקוד שחושב עבור הפריט.
     * @return ערך מספרי של הניקוד המחושב.
     */
    public double getCalculatedScore() 
    {
        return calculatedScore; 
    }

    /**
     * מעדכנת את הניקוד המחושב עבור הפריט.
     * @param calculatedScore ערך ניקוד חדש להגדרה.
     */
    public void setCalculatedScore(double calculatedScore) 
    { 
        this.calculatedScore = calculatedScore; 
    }
}