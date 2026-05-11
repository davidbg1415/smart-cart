package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * הסבר על הממשק:
 * ממשק זה מהווה את שכבת הגישה לנתונים עבור ישות המשתמש.
 * @author DAVID BEN GIGI
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> 
{
    /**
     * מציאת משתמש בודד על פי כתובת אימייל וסיסמה.
     * שימוש ב-Optional מאפשר טיפול בטוח במקרים בהם המשתמש לא נמצא.
     * 
     * @param email כתובת הדואר האלקטרוני של המשתמש.
     * @param password הסיסמה האישית של החשבון.
     * @return Optional המכיל את המשתמש במידה ונמצאה התאמה.
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * מציאת משתמש על פי כתובת אימייל בלבד.
     * משמש לבדיקת קיום משתמש בתהליך הרישום (Register).
     * 
     * @param email כתובת הדואר האלקטרוני.
     * @return Optional עם פרטי המשתמש אם הוא קיים.
     */
    Optional<User> findByEmail(String email);
}