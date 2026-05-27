package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.TagField;
import davidbg.smartcart.repositories.TagFieldRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * שכבת השירות (Service Layer) לניהול שדות המאפיינים (Tags) במערכת.
 * מחלקה זו אחראית על ניהול הגדרות השאלון הדינמי, המאפשר למנהל המערכת להגדיר
 * את אופן סיווג המוצרים במערכת בצורה גמישה וקלה לתחזוקה.
 * @author DAVID BEN GIGI
 */
@Service
public class TagFieldService {
    
    // ממשק הגישה לנתוני שדות התגיות במסד הנתונים
    private final TagFieldRepository repository;

    /**
     * בנאי המאתחל את השירות:
     * מבצע הזרקה של שכבת הגישה לנתונים (TagFieldRepository).
     * @param repository ממשק הגישה לנתוני שדות התגיות.
     */
    public TagFieldService(TagFieldRepository repository) {
        this.repository = repository;
    }

    /**
     * שליפת כל שדות התגיות הקיימים במערכת.
     * שיטה זו משמשת לטעינת הגדרות השאלון עבור ממשק המשתמש (למשל, עבור רכיבי ComboBox).
     * @return רשימה המכילה את כל שדות התגיות המוגדרים.
     */
    public List<TagField> getAllFields() { 
        return repository.findAll(); 
    }

    /**
     * שמירה או עדכון של שדה תגית במערכת.
     * שיטה זו משמשת את מנהל המערכת בעת הוספת שאלות חדשות לשאלון.
     * @param field אובייקט שדה התגית לשמירה.
     * @return אובייקט השדה שנשמר לאחר הקצאת מזהה ממסד הנתונים.
     */
    public TagField saveField(TagField field) { 
        return repository.save(field); 
    }

    /**
     * מחיקת שדה תגית מהמערכת על פי מזהה ייחודי.
     * @param id המזהה הייחודי של השדה להסרה.
     */
    public void deleteField(String id) { 
        repository.deleteById(id); 
    }
}