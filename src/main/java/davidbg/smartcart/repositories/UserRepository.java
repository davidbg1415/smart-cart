package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * הסבר על הממשק:
 * ממשק זה מהווה את שכבת הגישה לנתונים עבור ישות המשתמש.
 * הוא מאפשר ביצוע פעולות מול מסד הנתונים מונגו ללא צורך בכתיבת השאילתות באופן ידני.
 * * @author DAVID BEN GIGI
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> 
{
    /**
     * מציאת משתמש בודד על פי כתובת אימייל וסיסמה.
     * פעולה זו משמשת את המערכת לצורך אימות זהות המשתמש בתהליך ההתחברות.
     * * @param email כתובת הדואר האלקטרוני של המשתמש.
     * @param password הסיסמה האישית של החשבון.
     * @return אובייקט המשתמש במידה ונמצאה התאמה מלאה במסד הנתונים.
     */
    User findOneByEmailAndPassword(String email, String password);
}