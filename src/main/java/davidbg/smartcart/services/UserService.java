package davidbg.smartcart.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.repositories.UserRepository;

/**
 * הסבר על המחלקה:
 * מחלקה זו מהווה את שכבת השירות עבור ניהול המשתמשים במערכת.
 * השירות מטפל ברישום משתמשים חדשים, אימות פרטי התחברות וניהול פרופיל המשתמש.
 * * @author DAVID BEN GIGI
 */
@Service
public class UserService 
{
   // ממשק הגישה לנתוני המשתמשים במסד הנתונים
   private UserRepository userRepository;

   /**
    * בנאי המאתחל את השירות:
    * מבצע הזרקה של שכבת הגישה לנתונים לצורך ביצוע פעולות מול אוסף המשתמשים.
    * * @param userRepository ממשק הגישה לנתוני המשתמשים.
    */
   public UserService(UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }

   /**
    * הוספת משתמש חדש למסד הנתונים:
    * מבצעת בדיקה אם המזהה כבר קיים, ואם לא - שומרת את המשתמש החדש.
    * * @param user אובייקט המשתמש המכיל את פרטי הרישום.
    * @return ערך בוליאני המציין אם המשתמש נוסף בהצלחה או שכבר היה קיים.
    */
   /// C (Create)
   public boolean addUserToDB(User user)
   {
      // בדיקה אם המשתמש כבר קיים לפי המזהה הייחודי שלו
      if (userRepository.existsById(user.getId()))
         return false;

      userRepository.insert(user);
      return true;
   }

   /**
    * שליפת כל המשתמשים הרשומים:
    * מחזירה רשימה מלאה של כל אובייקטי המשתמש הקיימים במערכת.
    * * @return רשימה מסוג מערך המכילה את כל המשתמשים.
    */
   /// R (Read/Retrieve)
   public ArrayList<User> getAllUsers()
   {
      return (ArrayList<User>)userRepository.findAll();
   }

   /**
    * בדיקה אם המשתמש קיים במערכת:
    * מבצעת אימות של כתובת האימייל והסיסמה מול מסד הנתונים.
    * * @param email כתובת הדואר האלקטרוני של המשתמש.
    * @param password הסיסמה האישית של החשבון.
    * @return ערך בוליאני המציין אם נמצא משתמש תואם.
    */
   public boolean isUserExists(String email, String password)
   {
      User user = userRepository.findOneByEmailAndPassword(email, password);
      return user != null;
   }

   /**
    * שליפת משתמש לפי פרטי התחברות:
    * מחזירה את אובייקט המשתמש המלא לאחר אימות אימייל וסיסמה.
    * * @param email כתובת הדואר האלקטרוני.
    * @param password הסיסמה האישית.
    * @return אובייקט המשתמש במידה והפרטים נכונים, אחרת מחזירה ערך ריק.
    */
   public User getUser(String email, String password)
   {
      return userRepository.findOneByEmailAndPassword(email, password);
   }

   /**
    * מחיקת משתמש מהמערכת:
    * מסירה לצמיתות את המשתמש המבוקש ממסד הנתונים.
    * * @param user אובייקט המשתמש המיועד למחיקה.
    */
   /// D (Delete)
   public void deleteUser(User user)
   {
      userRepository.delete(user);
   }
}