package davidbg.smartcart.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.ProductService;
import davidbg.smartcart.services.UserService;
import davidbg.smartcart.services.WeatherService;


/**
 * מחלקת HomeView מייצגת את דף הנחיתה הראשי של האפליקציה.
 * הדף מציג תוכן דינמי המשתנה בהתאם למצב ההתחברות של המשתמש (אורח לעומת משתמש רשום).
 */
@Route(value = "", layout = MainLayout.class) // הגדרת הנתיב כדף הבית תחת תבנית MainLayout
@PageTitle("דף הבית | SmartCart")
public class HomeView extends VerticalLayout 
{
    // 1. עדכון משתני המחלקה והזרקתם בקונסטרקטור
    private final WeatherService weatherService;
    private final ProductService productService;
    private final UserService userService;

    /**
     * קונסטרקטור דף הבית - Spring יזריק לכאן אוטומטית את הסרוויסים
     */
    public HomeView(WeatherService weatherService, ProductService productService ,UserService userService ) 
    {
        this.weatherService = weatherService;
        this.productService = productService;
        this.userService = userService;

        // 1. שליפת אובייקט המשתמש הנוכחי מה-VaadinSession לצורך זיהוי
        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");

        // 2. הגדרות עיצוב ויזואליות לדף הראשי
        setAlignItems(Alignment.CENTER); // מרכוז כל הרכיבים במישור האופקי
        setPadding(false); // ביטול ריווח פנימי כברירת מחדל
        setSpacing(false); // ביטול מרווחים אוטומטיים בין רכיבים
        setSizeFull(); // פריסת הדף על כל שטח המסך הזמין
        
        // הגדרת רקע מדורג (Gradient) למראה מודרני
        getStyle().set("background", "linear-gradient(to bottom, #ffffff, #f1f5f9)");

        // 4. יצירת מיכל לתוכן המרכזי של הדף כדי לשמור על גבולות רוחב נוחים לקריאה
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.CENTER);
        content.setMaxWidth("1000px");

        // 5. לוגיקה לבניית התצוגה: אם אין משתמש בסשן - הצג דף אורח, אחרת הצג דף משתמש
        if (currentUser == null) 
        {
            buildGuestView(content);
        } 
        else 
        {
            buildAuthenticatedView(content, currentUser);
        }

        // הוספת מיכל התוכן לדף הראשי
        add(content);
    }

    
    /**
     * בניית תצוגת האורח (Guest View).
     */
    private void buildGuestView(VerticalLayout layout) 
    {
        H1 title = new H1("SmartCart");
        title.getStyle().set("font-size", "3.5em").set("margin-bottom", "0");
        
        H3 subTitle = new H3("המערכת החכמה להתאמת סל קניות אישי");
        subTitle.getStyle().set("color", "#64748b");

        Span creatorInfo = new Span("נוצר ע\"י: דוד בן גיגי");
        creatorInfo.getStyle()
                .set("font-weight", "bold")
                .set("color", "#1e293b")
                .set("margin-bottom", "20px");

        Image welcomeImg = new Image("images/welcome-banner.jpg", "SmartCart");
        welcomeImg.setWidth("550px");
        welcomeImg.getStyle()
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 20px rgba(0,0,0,0.1)");

        FlexLayout actions = new FlexLayout();
        actions.getStyle().set("gap", "20px");
        actions.setJustifyContentMode(JustifyContentMode.CENTER);
        actions.getStyle().set("margin-top", "30px");

        Button loginBtn = new Button("התחברות / הרשמה", VaadinIcon.USER.create(), 
                e -> UI.getCurrent().navigate("login"));
        loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);

        Button browseBtn = new Button("צפייה בקטלוג", VaadinIcon.SEARCH.create(), 
                e -> UI.getCurrent().navigate("shop"));
        browseBtn.addThemeVariants(ButtonVariant.LUMO_LARGE);

        actions.add(loginBtn, browseBtn);
        
        layout.add(title, subTitle, creatorInfo, welcomeImg, actions);
    }

    /**
     * בניית תצוגת משתמש מזוהה (Authenticated View).
     * משולבת עם ווידג'ט המלצות מזג אוויר ייעודי.
     */
   /**
 * בניית תצוגת משתמש מזוהה - כעת רכיב מזג האוויר ממוקם מתחת לכרטיסי הניווט
 */
private void buildAuthenticatedView(VerticalLayout layout, User user) 
{
    H1 welcome = new H1("ברוך הבא  " + user.getFullName());
    welcome.getStyle().set("margin-top", "30px").set("margin-bottom", "5px");
    
    Span info = new Span("מה תרצה לעשות");
    info.getStyle().set("color", "#64748b").set("margin-bottom", "15px");

    // 1. יצירת גריד כרטיסי הניווט (התפריט הראשי)
    FlexLayout menuGrid = new FlexLayout();
    menuGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
    menuGrid.setJustifyContentMode(JustifyContentMode.CENTER);
    menuGrid.getStyle().set("gap", "20px").set("padding", "10px 0");

    menuGrid.add(createMenuCard("חנות בגדים", "חיפוש וסינון פריטים ידני", VaadinIcon.SHOP, "shop"));
    menuGrid.add(createMenuCard("סל חכם", "הפעלת אלגוריתם התאמה", VaadinIcon.MAGIC, "smart-cart"));
    menuGrid.add(createMenuCard("היסטוריה", "הזמנות קודמות שלי", VaadinIcon.TIME_BACKWARD, "history"));


    // 2. יצירת רכיב המלצות מזג האוויר והזנת הנתונים
    WeatherRecommendationSection weatherSection = new WeatherRecommendationSection(
            weatherService, productService, userService
    );
    
    // שליחת העיר של המשתמש (אם היא null, המתודה בפנים תדע להציג את טופס הרישום לבד!)
    weatherSection.loadRecommendations(user.getCity());

    // 3. הוספה ל-Layout: קודם כל הברכה, אחר כך הלחצנים, ובתחתית הבאנר הקטן של מזג האוויר
    layout.add(welcome, info, menuGrid, weatherSection);
}
    /**
     * פונקציית עזר ליצירת כרטיס תפריט מעוצב.
     */
    private VerticalLayout createMenuCard(String title, String desc, VaadinIcon vIcon, String route) 
    {
        VerticalLayout card = new VerticalLayout();
        card.setWidth("240px");
        card.setAlignItems(Alignment.CENTER);
        
        card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 6px rgba(0,0,0,0.05)")
                .set("cursor", "pointer")
                .set("padding", "20px");

        Icon icon = vIcon.create();
        icon.setSize("40px");
        icon.setColor("#2563eb");

        Span titleLabel = new Span(title);
        titleLabel.getStyle().set("font-weight", "bold");

        Span descLabel = new Span(desc);
        descLabel.getStyle()
                .set("font-size", "0.85em")
                .set("color", "#64748b")
                .set("text-align", "center");

        card.add(icon, titleLabel, descLabel);
        
        card.addClickListener(e -> UI.getCurrent().navigate(route));
        
        card.getElement().executeJs("this.addEventListener('mouseenter', function() { this.style.backgroundColor = '#f8fafc'; });");
        card.getElement().executeJs("this.addEventListener('mouseleave', function() { this.style.backgroundColor = 'white'; });");

        return card;
    }
}