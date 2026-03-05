package davidbg.smartcart.datamodels;

/**
 * הסבר על המחלקה:
 * מחלקה זו מייצגת כתובת פיזית של משתמש או יעד למשלוח הזמנה.
 * בשימוש במסד הנתונים, מחלקה זו מוטמעת כחלק ממסמכים אחרים (כגון משתמש או הזמנה)
 * * @author DAVID BEN GIGI
 */
public class Address 
{
    private String city;    // שם העיר למשלוח
    private String street;  // שם הרחוב ומספר הבית
    private String zip;     // קוד מיקוד (Zip Code)

   /**
     * בנאי ריק:
     * משמש את המערכת ליצירת אובייקט חדש ללא נתונים ראשוניים.
     * הכרחי לצורך פעולת הספריות של ספרינג ומסד הנתונים.
     */
    public Address() {}

    /**
     * בנאי מאתחל עם פרמטרים.
     * * @param city   שם העיר המוגדרת בכתובת.
     * @param street שם הרחוב ומספר הבית.
     * @param zip    המיקוד של האזור הגאוגרפי.
     */
    public Address(String city, String street, String zip) 
    {
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    /**
     * מחזירה את שם העיר.
     * @return מחרוזת המייצגת את העיר.
     */
    public String getCity()
    { 
        return city; 
    }

    /**
     * מעדכנת את שם העיר.
     * @param city שם העיר החדש להגדרה.
     */
    public void setCity(String city)
    { 
        this.city = city; 
    }

    /**
     * מחזירה את שם הרחוב.
     * @return מחרוזת המייצגת את הרחוב והמספר.
     */
    public String getStreet()
    {
        return street; 
    }

    /**
     * מעדכנת את שם הרחוב.
     * @param street שם הרחוב והמספר החדשים להגדרה.
     */
    public void setStreet(String street) 
    { 
        this.street = street; 
    }

    /**
     * מחזירה את המיקוד.
     * @return מחרוזת המייצגת את קוד המיקוד.
     */
    public String getZip() 
    { 
        return zip; 
    }

    /**
     * מעדכנת את המיקוד.
     * @param zip קוד המיקוד החדש להגדרה.
     */
    public void setZip(String zip) 
    { 
        this.zip = zip; 
    }
}