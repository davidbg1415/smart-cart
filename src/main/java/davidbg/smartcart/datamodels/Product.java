package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * הסבר על המחלקה:
 * מחלקה זו מייצגת מוצר בודד בקטלוג הבגדים של המערכת.
 * המידע נשמר במסד הנתונים מונגו תחת אוסף המוצרים.
 * * @author DAVID BEN GIGI
 */
@Document(collection = "Products")
public class Product 
{
    @Id
    private String id;              // מזהה ייחודי של המוצר במסד הנתונים
    private String name;            // שם פריט הלבוש
    private String imageUrl;        // קישור לתמונת המוצר להצגה בתצוגה
    private String description;     // תיאור מפורט של הפריט
    private int price;              // מחיר המוצר המיוצג כמספר שלם ביחידות של אגורות
    private List<String> tags;      // רשימת תגיות לסיווג המוצר (למשל: חורף, אלגנט)

    @Transient
    private double temporaryScore;  // ניקוד דינמי המחושב בזמן ריצה בלבד ואינו נשמר במסד הנתונים

    /**
     * בנאי ברירת מחדל:
     * נדרש על ידי המערכת לצורך יצירת אובייקט ריק ושליפת נתונים.
     */
    public Product() {}

    /**
     * בנאי מאתחל עם פרמטרים:
     * מאפשר יצירת מוצר חדש עם כל הפרטים הנדרשים בשורה אחת.
     * * @param id מזהה המוצר.
     * @param name שם המוצר.
     * @param imageUrl קישור לתמונה.
     * @param description תיאור המוצר.
     * @param price מחיר באגורות.
     * @param tags רשימת תגיות.
     */
    public Product(String id, String name, String imageUrl, String description, int price, List<String> tags) 
    {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.temporaryScore = 0.0; // אתחול הניקוד לערך ברירת מחדל
    }

    /**
     * מחזירה את המזהה הייחודי של המוצר.
     * @return מחרוזת של מפתח המוצר.
     */
    public String getId() 
    { 
        return id; 
    }

    /**
     * מעדכנת את המזהה הייחודי של המוצר.
     * @param id מזהה חדש להגדרה.
     */
    public void setId(String id) 
    { 
        this.id = id; 
    }

    /**
     * מחזירה את שם המוצר.
     * @return מחרוזת של שם הפריט.
     */
    public String getName() 
    { 
        return name; 
    }

    /**
     * מעדכנת את שם המוצר.
     * @param name שם חדש לעדכון.
     */
    public void setName(String name) 
    { 
        this.name = name; 
    }

    /**
     * מחזירה את הקישור לתמונת המוצר.
     * @return מחרוזת המכילה כתובת אינטרנט.
     */
    public String getImageUrl() 
    { 
        return imageUrl; 
    }

    /**
     * מעדכנת את הקישור לתמונת המוצר.
     * @param imageUrl כתובת תמונה חדשה.
     */
    public void setImageUrl(String imageUrl) 
    { 
        this.imageUrl = imageUrl; 
    }

    /**
     * מחזירה את תיאור המוצר.
     * @return מחרוזת עם תיאור הפריט.
     */
    public String getDescription() 
    { 
        return description; 
    }

    /**
     * מעדכנת את תיאור המוצר.
     * @param description תיאור חדש להגדרה.
     */
    public void setDescription(String description) 
    { 
        this.description = description; 
    }

    /**
     * מחזירה את מחיר המוצר באגורות.
     * @return ערך מספרי של המחיר.
     */
    public int getPrice() 
    { 
        return price; 
    }

    /**
     * מעדכנת את מחיר המוצר.
     * @param price מחיר חדש באגורות.
     */
    public void setPrice(int price) 
    { 
        this.price = price; 
    }

    /**
     * מחזירה את רשימת התגיות של המוצר.
     * @return רשימה של מחרוזות לסיווג.
     */
    public List<String> getTags() 
    { 
        return tags; 
    }

    /**
     * מעדכנת את רשימת התגיות של המוצר.
     * @param tags רשימת תגיות חדשה.
     */
    public void setTags(List<String> tags) 
    { 
        this.tags = tags; 
    }

    /**
     * מחזירה את הניקוד הזמני שחושב עבור המוצר.
     * @return ערך מספרי של הניקוד הדינמי.
     */
    public double getTemporaryScore() 
    { 
        return temporaryScore; 
    }

    /**
     * מעדכנת את הניקוד הזמני המחושב.
     * @param temporaryScore ערך ניקוד חדש לעדכון.
     */
    public void setTemporaryScore(double temporaryScore) 
    { 
        this.temporaryScore = temporaryScore; 
    }
}