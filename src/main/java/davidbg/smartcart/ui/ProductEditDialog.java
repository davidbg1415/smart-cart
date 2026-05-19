package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.services.ProductService;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * דיאלוג עריכת מוצר - כולל תיקון לבעיית השמירה ושדה תיאור.
 */
public class ProductEditDialog extends Dialog 
{
    public ProductEditDialog(ProductService service, Product product, Runnable onSave) 
    {
        // אם product הוא null, יוצרים אובייקט חדש. אם לא, משתמשים בקיים.
        final Product p = (product != null) ? product : new Product();
        
        setHeaderTitle(product == null ? "הוספת מוצר חדש" : "עריכת מוצר: " + p.getName());

        // שדות הקלט
        TextField nameField = new TextField("שם מוצר");
        nameField.setValue(p.getName() != null ? p.getName() : "");
        nameField.setWidthFull();

        NumberField priceField = new NumberField("מחיר (₪)");
        priceField.setValue(p.getPrice() / 10.0);
        priceField.setWidthFull();

        TextField imgUrlField = new TextField("כתובת תמונה (URL)");
        imgUrlField.setValue(p.getImageUrl() != null ? p.getImageUrl() : "");
        imgUrlField.setWidthFull();

        // שדה התיאור החדש
        TextArea descriptionField = new TextArea("תיאור הבגד");
        descriptionField.setPlaceholder("פרט כאן על הגזרה, הבד והסטייל...");
        descriptionField.setValue(p.getDescription() != null ? p.getDescription() : "");
        descriptionField.setWidthFull();
        descriptionField.setHeight("150px");

        TextArea tagsField = new TextArea("תגיות (הפרד בפסיק)");
        tagsField.setPlaceholder("למשל: גברים, חולצות, חורף");
        if (p.getTags() != null) tagsField.setValue(String.join(", ", p.getTags()));
        tagsField.setWidthFull();

        // כפתור השמירה עם הלוגיקה המתוקנת
        Button saveBtn = new Button("שמור שינויים", e -> 
        {
            try 
            {
                // עדכון ידני של כל השדות בתוך האובייקט
                p.setName(nameField.getValue());
                p.setPrice((int) (priceField.getValue() * 10));
                p.setImageUrl(imgUrlField.getValue());
                p.setDescription(descriptionField.getValue()); // שמירת התיאור
                
                // טיפול בתגיות
                if (tagsField.getValue() != null && !tagsField.getValue().isEmpty()) 
                {
                    p.setTags(Arrays.stream(tagsField.getValue().split(","))
                            .map(String::trim)
                            .filter(tag -> !tag.isEmpty())
                            .collect(Collectors.toList()));
                }

                // שליחה לשירות השמירה
                service.addProductToDB(p);
                
                Notification.show("המוצר " + p.getName() + " נשמר בהצלחה!");
                
                if (onSave != null) onSave.run(); // רענון הגריד באדמין
                close();
            } 
            catch (Exception ex) 
            {
                Notification.show("שגיאה בשמירה: " + ex.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });

        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveBtn.setWidthFull();

        Button cancelBtn = new Button("ביטול", e -> close());
        cancelBtn.setWidthFull();

        VerticalLayout layout = new VerticalLayout(nameField, priceField, imgUrlField, descriptionField, tagsField, saveBtn, cancelBtn);
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.setWidth("450px");
        
        add(layout);
    }
}