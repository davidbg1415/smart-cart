package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * הסבר על המחלקה:
 * מחלקה זו מייצגת משתמש רשום במערכת.
 * המידע כולל פרטים אישיים, פרטי התחברות והעדפות סגנון המשמשות את אלגוריתם סל הקניות החכם.
 * * @author DAVID BEN GIGI
 */
@Document(collection = "Users")
public class User
{
    @Id
    private String id;               // מזהה ייחודי של המשתמש במסד הנתונים
    private String fullName;         // שם מלא של המשתמש
    private String email;            // כתובת אימייל המשמשת גם כשם משתמש
    private String password;         // סיסמת התחברות (נשמרת בצורה מוצפנת)
    private String City;  // כתובת ברירת המחדל למשלוחים
    private Role role; // ADMIN, REGISTERED_USER

    /**
     * בנאי ברירת מחדל:
     * נדרש עבור טעינת נתונים ממסד הנתונים על ידי המערכת.
     */
    public User() {}

    /**
     * בנאי מאתחל עם פרמטרים:
     * מאפשר יצירת משתמש חדש במערכת עם כל הפרטים הנדרשים.
     * * @param id מזהה המשתמש.
     * @param fullName שם המשתמש המלא.
     * @param email כתובת דואר אלקטרוני.
     * @param password סיסמה מאובטחת.
     * @param phone מספר טלפון.
     * @param preferences רשימת העדפות.
     * @param defaultAddress כתובת למשלוח.
     */
    public User(String id, String fullName, String email, String password, String city ) 
    {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.City = city;
    }

    /**
     * מחזירה את המזהה הייחודי של המשתמש.
     * @return מחרוזת המייצגת את מפתח המשתמש.
     */
    public String getId() 
    { 
        return id; 
    }

    /**
     * מעדכנת את המזהה הייחודי של המשתמש.
     * @param id מזהה חדש להגדרה.
     */
    public void setId(String id) 
    { 
        this.id = id; 
    }

    /**
     * מחזירה את השם המלא של המשתמש.
     * @return מחרוזת של שם המשתמש.
     */
    public String getFullName() 
    { 
        return fullName; 
    }

    /**
     * מעדכנת את השם המלא של המשתמש.
     * @param fullName שם חדש לעדכון.
     */
    public void setFullName(String fullName) 
    { 
        this.fullName = fullName; 
    }

    /**
     * מחזירה את כתובת האימייל של המשתמש.
     * @return מחרוזת של כתובת הדואר האלקטרוני.
     */
    public String getEmail() 
    { 
        return email; 
    }

    /**
     * מעדכנת את כתובת האימייל של המשתמש.
     * @param email כתובת חדשה להגדרה.
     */
    public void setEmail(String email) 
    { 
        this.email = email; 
    }

    /**
     * מחזירה את הסיסמה המוצפנת של המשתמש.
     * @return מחרוזת המייצגת את הסיסמה.
     */
    public String getPassword() 
    { 
        return password; 
    }

    /**
     * מעדכנת סיסמה חדשה למשתמש.
     * @param password סיסמה חדשה להגדרה.
     */
    public void setPassword(String password) 
    { 
        this.password = password; 
    }

    public Role getRole() 
    { 
        return role; 
    }

    public void setRole(Role role) 
    { 
        this.role = role; 
    }

    public String getCity() 
    {
        return City;
    }

    public void setCity(String city) 
    {
        City = city;
    }
}