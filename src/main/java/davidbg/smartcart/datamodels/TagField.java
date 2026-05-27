package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * מחלקה המייצגת שדה מאפיינים דינמי במערכת.
 * מחלקה זו מאפשרת ניהול גמיש של תגיות השאלון דרך מסד הנתונים,
 * כך שמנהל המערכת יכול להוסיף או לעדכן קטגוריות סינון למוצרים ללא צורך בשינויי קוד.
 */
@Document(collection = "tag_fields")
public class TagField {
    
    // מזהה ייחודי של השדה במסד הנתונים
    @Id
    private String id;
    
    // שם השדה, למשל: "עונה", "צבע", או "סגנון"
    private String fieldName; 
    
    // רשימת האפשרויות הזמינות לבחירה עבור שדה זה (למשל: "קיץ", "חורף" עבור השדה "עונה")
    private List<String> options = new ArrayList<>(); 

    /**
     * בנאי ברירת מחדל נדרש לצורך עבודה מול מסד הנתונים.
     */
    public TagField() {}

    /**
     * בנאי לאתחול שדה מאפיינים חדש.
     * @param fieldName שם המאפיין.
     * @param options רשימת האפשרויות לבחירה.
     */
    public TagField(String fieldName, List<String> options) {
        this.fieldName = fieldName;
        this.options = options;
    }

    /**
     * מחזירה את המזהה הייחודי של השדה.
     * @return מזהה השדה.
     */
    public String getId() { return id; }

    /**
     * מגדירה את המזהה הייחודי של השדה.
     * @param id מזהה חדש.
     */
    public void setId(String id) { this.id = id; }

    /**
     * מחזירה את שם השדה.
     * @return שם השדה.
     */
    public String getFieldName() { return fieldName; }

    /**
     * מגדירה את שם השדה.
     * @param fieldName שם חדש לשדה.
     */
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    /**
     * מחזירה את רשימת האפשרויות לשדה זה.
     * @return רשימת האפשרויות.
     */
    public List<String> getOptions() { return options; }

    /**
     * מעדכנת את רשימת האפשרויות לשדה זה.
     * @param options רשימת אפשרויות חדשה.
     */
    public void setOptions(List<String> options) { this.options = options; }
}