package davidbg.smartcart.datamodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tag_fields")
public class TagField {
    @Id
    private String id;
    private String fieldName; // שם השדה: למשל "עונה", "צבע", "סגנון"
    private List<String> options = new ArrayList<>(); // האופציות בתוך ה-ComboBox

    public TagField() {}

    public TagField(String fieldName, List<String> options) {
        this.fieldName = fieldName;
        this.options = options;
    }

    // גטרים וסטרים רגילים
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
}