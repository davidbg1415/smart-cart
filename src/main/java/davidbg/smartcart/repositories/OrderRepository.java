package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * הסבר על הממשק:
 * ממשק זה משמש כשכבת הגישה לנתונים עבור ישות ההזמנה במסד הנתונים.
 * הוא מאפשר לשמור הזמנות חדשות ולשלוף את היסטוריית הרכישות של המשתמשים.
 * * @author DAVID BEN GIGI
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> 
{
    /**
     * מחזירה רשימה של כל ההזמנות שבוצעו על ידי משתמש ספציפי.
     * פעולה זו מאפשרת למערכת להציג למשתמש את היסטוריית הקניות האישית שלו.
     * * @param userId מזהה ייחודי של המשתמש שביצע את ההזמנות.
     * @return רשימה של אובייקטי הזמנה המשויכים לאותו מזהה משתמש.
     */
    List<Order> findByUserId(String userId);
}