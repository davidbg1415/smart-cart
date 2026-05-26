package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import davidbg.smartcart.datamodels.TagField;
import davidbg.smartcart.services.TagFieldService;

public class QuestionnaireAdminDialog extends Dialog 
{
    private final TagFieldService tagFieldService;
    private final VerticalLayout fieldsContainer = new VerticalLayout();
    private final Runnable onChangeCallback; // פונקציה שתרענן את דף השאלון הראשי כשנסגר

    public QuestionnaireAdminDialog(TagFieldService tagFieldService, Runnable onChangeCallback) {
        this.tagFieldService = tagFieldService;
        this.onChangeCallback = onChangeCallback;

        setHeaderTitle("ניהול שדות ואופציות השאלון (אדמין) ⚙️");
        setWidth("600px");
        setHeight("700px");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        // 1. אזור הוספת שדה חדש לגמרי (למשל תגית חדשה כמו "בד")
        TextField newFieldInput = new TextField("צור שדה תגית חדש:");
        newFieldInput.setPlaceholder("למשל: סוג בד, מותג...");
        Button addFieldBtn = new Button("הוסף שדה", VaadinIcon.PLUS.create(), e -> {
            String name = newFieldInput.getValue().trim();
            if (!name.isEmpty()) {
                tagFieldService.saveField(new TagField(name, java.util.List.of("ברירת מחדל")));
                newFieldInput.clear();
                refreshDialogFields();
            }
        });
        addFieldBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout createFieldBar = new HorizontalLayout(newFieldInput, addFieldBtn);
        createFieldBar.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END);

        layout.add(createFieldBar, new H3("השדות הקיימים והערכים שלהם:"), fieldsContainer);
        add(layout);

        // כפתור סגירה שמרענן את הדף הראשי
        Button closeBtn = new Button("סגור ושמור שינויים", e -> {
            onChangeCallback.run();
            close();
        });
        getFooter().add(closeBtn);

        refreshDialogFields();
    }

    private void refreshDialogFields() {
        fieldsContainer.removeAll();
        
        for (TagField tf : tagFieldService.getAllFields()) {
            VerticalLayout fieldRow = new VerticalLayout();
            fieldRow.getStyle().set("background-color", "#f1f5f9").set("border-radius", "8px").set("padding", "10px");

            // שורת כותרת השדה + כפתור מחיקת השדה כולו
            HorizontalLayout header = new HorizontalLayout();
            header.setWidthFull();
            header.setJustifyContentMode(HorizontalLayout.JustifyContentMode.BETWEEN);
            
            Span fieldNameSpan = new Span(tf.getFieldName());
            fieldNameSpan.getStyle().set("font-weight", "bold");
            
            Button deleteFieldBtn = new Button(VaadinIcon.TRASH.create(), e -> {
                tagFieldService.deleteField(tf.getId());
                refreshDialogFields();
            });
            deleteFieldBtn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY_INLINE);
            header.add(fieldNameSpan, deleteFieldBtn);

            // תצוגת האופציות הקיימות בשדה כ"תגיות" לחיצות למחיקה
            HorizontalLayout optionsLayout = new HorizontalLayout();
            optionsLayout.getStyle().set("flex-wrap", "wrap");
            for (String option : tf.getOptions()) {
                Button optBtn = new Button(option + " ✕", e -> {
                    tf.getOptions().remove(option);
                    tagFieldService.saveField(tf);
                    refreshDialogFields();
                });
                optBtn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
                optionsLayout.add(optBtn);
            }

            // שורת הוספת אופציה חדשה לתוך השדה הספציפי הזה
            TextField newOptInput = new TextField();
            newOptInput.setPlaceholder("הוסף אופציה...");
            Button addOptBtn = new Button(VaadinIcon.PLUS.create(), e -> {
                String opt = newOptInput.getValue().trim();
                if (!opt.isEmpty()) {
                    tf.getOptions().add(opt);
                    tagFieldService.saveField(tf);
                    refreshDialogFields();
                }
            });
            HorizontalLayout addOptBar = new HorizontalLayout(newOptInput, addOptBtn);
            addOptBar.setAlignItems(com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END);

            fieldRow.add(header, optionsLayout, addOptBar);
            fieldsContainer.add(fieldRow);
        }
    }
}