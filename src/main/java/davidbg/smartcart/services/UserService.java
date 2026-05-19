package davidbg.smartcart.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.datamodels.Role; 
import davidbg.smartcart.repositories.UserRepository;

/**
 * שכבת השירות המעודכנת לניהול משתמשים.
 * תומכת בהתחברות מבוססת Optional ובניהול תפקידים (Roles).
 * @author DAVID BEN GIGI
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * הוספת משתמש חדש:
     * מוודא שהאימייל ייחודי ומגדיר תפקיד "REGISTERED_USER" כברירת מחדל.
     */
    public boolean registerNewUser(User user) 
    {
        // בדיקה אם האימייל (שמשמש כשם משתמש) כבר קיים
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        // הגדרת תפקיד ברירת מחדל אם לא הוגדר
        if (user.getRole() == null) {
            user.setRole(Role.REGISTERED_USER);
        }

        userRepository.save(user);
        return true;
    }

    /**
     * אימות פרטי התחברות (Login):
     * מחזיר Optional כדי שה-UI יוכל לטפל במקרה של "לא נמצא" בקלות.
     */
    public Optional<User> login(String email, String password) {
        // מציאת משתמש שתואם גם לאימייל וגם לסיסמה
        return userRepository.findByEmailAndPassword(email, password);
    }

    /**
     * שליפת כל המשתמשים.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * מחיקת משתמש.
     */
    public void deleteUser(User user) 
    {
        userRepository.delete(user);
    }

     /**
     * מחיקת משתמש.
     */
    public void updateUser(User user) 
    {
        userRepository.save(user);
    }
}