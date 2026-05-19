package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.services.ProductService;
import davidbg.smartcart.utilities.MainLayout;
import davidbg.smartcart.utilities.ProductCard;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * מחלקת ShopView מייצגת את ממשק חנות הבגדים של המערכת.
 * הדף כולל סרגל צידי לסינון לפי מגדר וקטגוריה, ושורת חיפוש טקסטואלית לעדכון דינמי של המוצרים.
 */
@Route(value = "shop", layout = MainLayout.class) // קישור לדף הבית תחת תבנית הניווט הראשית
@PageTitle("חנות בגדים | SmartCart")
public class ShopView extends HorizontalLayout 
{

    private final ProductService productService;
    
    // רכיבי ממשק המשתמש הנשמרים כמשתני מחלקה לצורך עדכון דינמי
    private final FlexLayout productGrid = new FlexLayout(); // גריד גמיש להצגת כרטיסי המוצרים
    private final TextField searchField = new TextField(); // שדה חיפוש טקסטואלי
    private final VerticalLayout subCategoryContainer = new VerticalLayout(); // מיכל לכפתורי תת-קטגוריה

    // משתני מצב לשמירת הבחירות הנוכחיות של המשתמש בסינון
    private String selectedGender = null;
    private String selectedCategory = null;

    /**
     * קונסטרקטור דף החנות.
     * @param productService שירות הטיפול במוצרים המוזרק מהקונטיינר של Spring.
     */
    public ShopView(@Autowired ProductService productService) 
    {
        this.productService = productService;

        // הגדרות עיצוב כלליות למכולה הראשית (HorizontalLayout)
        setSizeFull(); // פריסה על כל גובה ורוחב המסך
        setSpacing(false); // ביטול מרווחים אוטומטיים בין הסרגל הצידי לתוכן המרכזי
        getStyle().set("background-color", "#f8fafc"); // צבע רקע אפור בהיר

        // 1. יצירת סרגל הניווט הצידי (Sidebar) המכיל את הסינונים
        VerticalLayout sidebar = createSidebar();

        // 2. יצירת אזור התוכן המרכזי הכולל את שורת החיפוש וגריד המוצרים
        VerticalLayout mainContent = createMainContent();

        // הוספת הרכיבים לדף והגדרת יחסי גדילה
        add(sidebar, mainContent);
        setFlexGrow(1, mainContent); // מאפשר לתוכן המרכזי לתפוס את יתרת המקום במסך

        // טעינה ראשונית של כל המוצרים ללא פילטרים
        updateList();
    }

    /**
     * יצירת סרגל הניווט הצידי של החנות.
     * @return אובייקט VerticalLayout המעוצב כסרגל צידי.
     */
    private VerticalLayout createSidebar() 
    {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("280px"); // רוחב קבוע לסרגל
        sidebar.setHeightFull();
        
        // עיצוב הסרגל: רקע לבן וקו מפריד בצד שמאל (מתאים ליישור מימין לשמאל)
        sidebar.getStyle()
                .set("background-color", "white")
                .set("border-left", "1px solid #e2e8f0")
                .set("padding", "20px");

        H2 title = new H2("SmartCart");
        title.getStyle().set("color", "#1e293b").set("font-size", "1.5em");

        // כפתור לאיפוס כל הסינונים והצגת כל הקטלוג
        Button allBtn = new Button("כל המוצרים", VaadinIcon.HOME.create(), e -> 
        {
            selectedGender = null;
            selectedCategory = null;
            subCategoryContainer.removeAll(); // ניקוי תפריט תת-הקטגוריות
            updateList();
        });
        allBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        allBtn.setWidthFull();

        // יצירת כפתורי סינון לפי מגדר
        Button menBtn = new Button("גברים", VaadinIcon.MALE.create(), e -> showSubCategories("גברים"));
        Button womenBtn = new Button("נשים", VaadinIcon.FEMALE.create(), e -> showSubCategories("נשים"));
        
        menBtn.setWidthFull();
        womenBtn.setWidthFull();

        // הוספת הרכיבים לסרגל הצידי
        sidebar.add(title, new Hr(), allBtn, menBtn, womenBtn, new Hr(), subCategoryContainer);
        return sidebar;
    }

    /**
     * הצגת תת-קטגוריות רלוונטיות בהתאם למגדר שנבחר.
     * @param gender המגדר שנבחר ("גברים" או "נשים").
     */
    private void showSubCategories(String gender) 
    {
        this.selectedGender = gender;
        this.selectedCategory = null; // איפוס קטגוריה ספציפית בעת החלפת מגדר
        subCategoryContainer.removeAll(); // ניקוי כפתורים קודמים

        // כותרת לתפריט המשני
        H2 subTitle = new H2("קטגוריות " + gender);
        subTitle.getStyle().set("font-size", "1em").set("color", "#64748b");
        subCategoryContainer.add(subTitle);

        // לוגיקה לבחירת תת-הקטגוריות להצגה לפי המגדר
        if (gender.equals("גברים")) 
        {
            createSubBtn("חולצות");
            createSubBtn("מכנסיים");
            createSubBtn("נעליים");
            createSubBtn("ג'ינס");
        } 
        else 
        {
            createSubBtn("שמלות");
            createSubBtn("חצאיות");
            createSubBtn("חולצות");
            createSubBtn("תיקים");
        }
        
        // עדכון הרשימה בהתאם למגדר שנבחר
        updateList();
    }

    /**
     * פונקציית עזר ליצירת כפתור תת-קטגוריה בתוך הסרגל הצידי.
     * @param categoryName שם הקטגוריה.
     */
    private void createSubBtn(String categoryName) 
    {
        Button btn = new Button(categoryName, e -> 
        {
            this.selectedCategory = categoryName;
            updateList();
        });
        
        // עיצוב כפתור קטן ועדין ללא מסגרת בולטת
        btn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        subCategoryContainer.add(btn);
    }

    /**
     * יצירת אזור התוכן המרכזי (שורת חיפוש וגריד מוצרים).
     * @return אובייקט VerticalLayout הכולל את גוף הדף.
     */
    private VerticalLayout createMainContent() 
    {
        VerticalLayout container = new VerticalLayout();
        container.setPadding(true);

        // הגדרת שורת חיפוש טקסטואלית עם אייקון זכוכית מגדלת
        searchField.setPlaceholder("חפש פריט לבוש...");
        searchField.setWidthFull();
        searchField.setMaxWidth("600px");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        
        // הגדרת מצב LAZY - עדכון הרשימה מתבצע רק לאחר שהמשתמש מפסיק להקליד לזמן קצר
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());

        // הגדרת גריד המוצרים (FlexLayout)
        productGrid.setFlexWrap(FlexWrap.WRAP); // מאפשר ירידת שורה אוטומטית של כרטיסים
        productGrid.getStyle().set("gap", "20px"); // מרווח בין כרטיס לכרטיס
        productGrid.setJustifyContentMode(JustifyContentMode.CENTER); // מרכוז הגריד
        
        // יצירת אזור גלילה (Scroll) המכיל את הגריד
        VerticalLayout scrollArea = new VerticalLayout(productGrid);
        scrollArea.setHeightFull();
        scrollArea.getStyle().set("overflow-y", "auto"); // הפעלת גלילה אנכית בלבד

        // הוספת הרכיבים למכולה המרכזית
        container.add(searchField, scrollArea);
        container.setHorizontalComponentAlignment(Alignment.CENTER, searchField);
        
        return container;
    }

    /**
     * עדכון רשימת המוצרים המוצגת בגריד.
     * הפונקציה שואבת את הנתונים מה-Service לפי הפילטרים שנבחרו ומייצרת כרטיסי מוצר.
     */
    private void updateList() 
    {
        // 1. ניקוי הגריד הנוכחי מכל הכרטיסים הקודמים
        productGrid.removeAll();
        
        // 2. שליפת רשימת מוצרים מסוננת מה-Service
        List<Product> products = productService.findFiltered(
                searchField.getValue(), 
                selectedGender, 
                selectedCategory
        );

        // 3. יצירת כרטיס (ProductCard) עבור כל מוצר שנמצא והוספתו לגריד
        for (Product p : products) 
        {
            productGrid.add(new ProductCard(p));
        }
    }
}