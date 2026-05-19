package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.*;
import davidbg.smartcart.services.*;
import davidbg.smartcart.utilities.MainLayout;
import davidbg.smartcart.utilities.ProductCard;


import java.util.ArrayList;
import java.util.List;

/**
 * מחלקת SmartCartView - המוח של המערכת.
 * דף זה מאפשר למשתמש לענות על שאלון סטייל, להגדיר אילוצי תקציב וכמות,
 * ולהריץ אלגוריתם חכם לבניית חבילת ביגוד אופטימלית במיוחד עבורו.
 */
@Route(value = "smart-cart", layout = MainLayout.class)
@PageTitle("בונה החבילות | SmartCart")
public class SmartCartView extends VerticalLayout implements BeforeEnterObserver
{
    // שירותי המערכת המוזרקים
    private final SmartCartAlgoService dpService; // אלגוריתם ה-Knapsack
    private final ProductService productService;     // גישה למלאי
    private final ScoringService scoringService;     // חישוב ציוני התאמה
    private final TagFieldService tagFieldService;   // ניהול שדות השאלון הדינמיים

    // רכיבי שדות השאלון הדינמיים
    private final VerticalLayout fieldsContainer = new VerticalLayout(); 
    private final List<Select<String>> activeSelects = new ArrayList<>();

    // רכיבי אילוצים (תקציב וכמויות קבועים)
    private NumberField budget = new NumberField("תקציב (₪)");
    private IntegerField minItems = new IntegerField("מינימום פריטים");
    private IntegerField maxItems = new IntegerField("מקסימום פריטים");
    
    // רכיבי תצוגת תוצאות
    private FlexLayout resultGrid = new FlexLayout(); // גריד להצגת הכרטיסים
    private Span summaryLabel = new Span(); // סיכום טקסטואלי
    private List<Product> resultBundle = new ArrayList<>(); // רשימת התוצאה הסופית

    /**
     * קונסטרקטור לבניית המסך - הוספנו כאן את הזרקת ה-TagFieldService
     */
    public SmartCartView(SmartCartAlgoService dpService, ProductService productService, 
                         ScoringService scoringService, TagFieldService tagFieldService) 
    {
        this.dpService = dpService;
        this.productService = productService;
        this.scoringService = scoringService;
       
        this.tagFieldService = tagFieldService;

        setAlignItems(Alignment.CENTER);
        setPadding(true);

        // כותרת הדף
        H2 title = new H2("בונה החבילות החכם - שאלון סטייל");
        title.getStyle().set("color", "#1e293b");
        add(title);

        // שינוי אדמין: אם מחובר אדמין, נציג לו כפתור ייעודי לפתיחת חלונית העריכה בראש הדף
        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");
        if (currentUser != null && currentUser.getRole() == Role.ADMIN) 
        {
            Button adminEditBtn = new Button("⚙️ ניהול שדות השאלון (אדמין)", e -> {
                new QuestionnaireAdminDialog(tagFieldService, this::buildDynamicQuestionnaire).open();
            });
            adminEditBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
            adminEditBtn.getStyle().set("margin-bottom", "15px");
            add(adminEditBtn);
        }

        // הגדרת מיכל שדות השאלון הדינמיים
        fieldsContainer.setAlignItems(Alignment.CENTER);
        fieldsContainer.setPadding(false);
        fieldsContainer.setSpacing(true);

        // בנייה וטעינה של השאלון מתוך מסד הנתונים
        buildDynamicQuestionnaire();

        // שורת אילוצי המשתמש (תקציב וכמויות)
        HorizontalLayout row3 = new HorizontalLayout(budget, minItems, maxItems);
        row3.setAlignItems(Alignment.END);
        
        // כפתור הפעלה מעוצב (ירוק)
        Button calculateBtn = new Button("חשב סל אופטימלי", VaadinIcon.MAGIC.create());
        calculateBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        calculateBtn.getStyle().set("background-color", "#28a745").set("margin-top", "20px");

        // הפעלת האלגוריתם עם ראנר התקדמות
        calculateBtn.addClickListener(e -> runSmartAlgoWithProgress());

        // הגדרת הגריד להצגת התוצאות (כרטיסי מוצר)
        resultGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        resultGrid.setJustifyContentMode(JustifyContentMode.CENTER);
        resultGrid.getStyle().set("gap", "20px").set("margin-top", "30px");

        // הוספת כל הרכיבים לדף (החלפנו את row1 ו-row2 ב-fieldsContainer הדינמי)
        add(fieldsContainer, row3, calculateBtn, summaryLabel, resultGrid);
    }

    /**
     * בונה ומאכלס את שדות השאלון דינמית מתוך מסד הנתונים.
     * אם ה-DB ריק בהרצה ראשונה, הוא יוצק אוטומטית את ערכי ברירת המחדל המקוריים שלך.
     */
    private void buildDynamicQuestionnaire() 
{
    fieldsContainer.removeAll();
    activeSelects.clear();

    // שליפת השדות החיים מהמסד
    List<TagField> fields = tagFieldService.getAllFields();
    
    HorizontalLayout currentRow = new HorizontalLayout();
    currentRow.setSpacing(true);
    int count = 0;

    for (TagField tf : fields) 
    {
        Select<String> select = new Select<>();
        select.setLabel(tf.getFieldName());
        select.setItems(tf.getOptions());
        select.setWidth("200px");

        activeSelects.add(select);
        currentRow.add(select);
        count++;

        // חלוקה אסתטית: כל 4 שדות קומבובוקס נפתח שורה חדשה בטופס
        if (count % 4 == 0) 
        {
            fieldsContainer.add(currentRow);
            currentRow = new HorizontalLayout();
            currentRow.setSpacing(true);
        }
    }
    
    if (currentRow.getComponentCount() > 0) 
    {
        fieldsContainer.add(currentRow);
    }

    // ערכי ברירת מחדל לאילוצים
    budget.setPlaceholder("למשל 500");
    minItems.setValue(1);
    maxItems.setValue(3);
}


    /**
     * הרצת האלגוריתם בתוך Thread נפרד עם הצגת ProgressBar ואחוזים.
     */
   /**
 * הרצת האלגוריתם בתוך Thread נפרד עם הצגת ProgressBar ואחוזים.
 */
private void runSmartAlgoWithProgress() 
{
    // בדיקת קלט תקציב
    if (budget.getValue() == null || budget.getValue() <= 0) 
    {
        Notification.show("נא להזין תקציב תקין");
        return;
    }

    // יצירת דיאלוג "ראנר" עם אחוזים
    Dialog progressDialog = new Dialog();
    progressDialog.setCloseOnOutsideClick(false);
    
    ProgressBar progressBar = new ProgressBar();
    progressBar.setValue(0);
    
    Span statusText = new Span("מתחיל בחישוב...");
    VerticalLayout progressLayout = new VerticalLayout(new H3("האלגוריתם בונה עבורך חבילה..."), progressBar, statusText);
    progressLayout.setAlignItems(Alignment.CENTER);
    
    progressDialog.add(progressLayout);
    progressDialog.open();

    // הגדרת המשימה לביצוע בתהליכון נפרד
    Runnable task = () -> 
    {
        try 
        {
            // שלב 1: איסוף נתונים דינמי (20%)
            updateStatus(progressDialog, progressBar, statusText, 0.2, "מנתח שאלון סטייל...", 20);
            
            // יצירת רשימת תגיות שנבחרו בשאלון בפועל
            List<String> selectedTags = new ArrayList<>();
            for (Select<String> select : activeSelects) 
            {
                String value = select.getValue();
                if (value != null && !value.trim().isEmpty()) 
                {
                    selectedTags.add(value.trim());
                }
            }
            Thread.sleep(600);

            // שלב 2: חישוב ציוני התאמה מבוסס רשימת תגיות (50%)
            updateStatus(progressDialog, progressBar, statusText, 0.5, "מחשב ציוני התאמה לכל המלאי...", 50);
            List<Product> allProducts = productService.getAllProducts();
            
            // כאן אתה מעביר את רשימת התגיות ישירות לסרוויס הציון שלך
            scoringService.calculateScoresBySurvey(allProducts, selectedTags); 
            Thread.sleep(800);

            // שלב 3: הרצת ה-DP התלת-ממדי (80%)
            updateStatus(progressDialog, progressBar, statusText, 0.8, "מבצע אופטימיזציה לתקציב ...", 80);
            int budgetUnits = (int) Math.round(budget.getValue() * 10);
            resultBundle = dpService.getOptimalBundle(allProducts, budgetUnits, minItems.getValue(), maxItems.getValue());
            Thread.sleep(1000);

            // שלב 4: סיום (100%)
            updateStatus(progressDialog, progressBar, statusText, 1.0, "החבילה מוכנה!", 100);
            Thread.sleep(500);

            // חזרה ל-UI Thread להצגת התוצאה
            getUI().ifPresent(ui -> ui.access(() -> 
            {
                displayResults();
                progressDialog.close();
            }));
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    };

    new Thread(task).start();
}

    /**
     * פונקציית עזר לעדכון הראנר מה-Thread.
     */
    private void updateStatus(Dialog dialog, ProgressBar bar, Span label, double val, String text, int percent) 
    {
        getUI().ifPresent(ui -> ui.access(() -> 
        {
            bar.setValue(val);
            label.setText(text + " (" + percent + "%)");
        }));
    }

    /**
     * הצגת תוצאות האלגוריתם באמצעות כרטיסי מוצר קומפקטיים וגמישים.
     */
    private void displayResults() 
    {
        resultGrid.removeAll();
        
        if (resultBundle.isEmpty()) 
        {
            summaryLabel.setText("לא נמצא שילוב אופטימלי תחת האילוצים. נסה להגדיל את התקציב.");
            return;
        }

        // חישובים כלליים להצגת סיכום החבילה המוצעת
        double totalCost = resultBundle.stream().mapToDouble(p -> p.getPrice() / 10.0).sum();
        double avgMatch = resultBundle.stream().mapToDouble(Product::getTemporaryScore).average().orElse(0);
        
        summaryLabel.setText(String.format("החבילה המוצעת עבורך: %d פריטים | עלות החבילה: %.2f ₪ | התאמה ממוצעת: %.1f%%", 
                resultBundle.size(), totalCost, avgMatch));
        summaryLabel.getStyle().set("font-weight", "bold").set("margin-top", "20px");

        // הוספת כרטיס לכל מוצר שנבחר - שימוש חוזר מושלם ב-ProductCard (קומפקטי = true)!
        for (Product p : resultBundle) 
        {
            ProductCard card = new ProductCard(p, true); 
            resultGrid.add(card);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) 
    {
        if (VaadinSession.getCurrent().getAttribute("user") == null) 
        {
            event.rerouteTo(""); // חסימת גישה לאורחים
        }
    }
}