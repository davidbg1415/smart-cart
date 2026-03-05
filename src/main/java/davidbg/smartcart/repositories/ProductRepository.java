package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * הסבר על הממשק:
 * ממשק זה משמש כשכבת הגישה לנתונים עבור ישות המוצר במסד הנתונים.
 * הוא מאפשר שליפה, שמירה וניהול של קטלוג המוצרים במערכת.
 * * @author DAVID BEN GIGI
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> 
{
    /**
     * מחזירה רשימה של מוצרים המכילים תגית ספציפית בתוך מערך התגיות שלהם.
     * פעולה זו משמשת את המערכת לצורך התאמת מוצרים להעדפות האישיות של המשתמש.
     * * @param tag מחרוזת המייצגת את התגית לחיפוש (למשל: חורף).
     * @return רשימה של אובייקטי מוצר התואמים לתגית המבוקשת.
     */
    List<Product> findByTagsContaining(String tag);
}