package davidbg.smartcart.ui;

import java.util.List;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.Role;
import davidbg.smartcart.datamodels.User;

/**
 * מחלקת ה-Layout הראשית של האפליקציה.
 * המחלקה מגדירה את סרגל הניווט העליון המשותף לכל דפי האתר.
 * היא מממשת את BeforeEnterObserver כדי לעדכן את התצוגה בכל מעבר דף.
 */
public class MainLayout extends AppLayout implements BeforeEnterObserver 
{

    // מיכל ראשי לסרגל הניווט העליון
    private final HorizontalLayout headerContainer = new HorizontalLayout();
    
    // רכיב הטקסט המציג את כמות הפריטים בסל (העיגול האדום)
    private Span cartBadge;

    /**
     * קונסטרקטור ה-Layout.
     * מגדיר את השלד הראשוני של ה-Header ומוסיף אותו ל-Navbar של ה-AppLayout.
     */
    public MainLayout() 
    {
        headerContainer.setWidthFull();
        headerContainer.setPadding(true);
        
        // הגדרת עיצוב: רקע לבן וצללית עדינה בתחתית הסרגל
        headerContainer.getStyle()
                .set("background-color", "white")
                .set("box-shadow", "0 2px 5px rgba(0,0,0,0.1)");
        
        addToNavbar(headerContainer);
    }

    /**
     * פונקציה הרצה לפני הכניסה לכל View שמשתמש ב-Layout זה.
     * תפקידה לרענן את ה-Header כדי לשקף שינויים במצב המשתמש (מחובר/מנותק).
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) 
    {
        headerContainer.removeAll(); // ניקוי הרכיבים הישנים
        createHeader();              // בנייה מחדש של הסרגל לפי נתוני הסשן הנוכחיים
    }

    /**
     * בניית רכיבי ה-Header: תפריט ניווט ואזור משתמש אישי.
     */
    private void createHeader() 
    {
        // שליפת המשתמש הנוכחי מהסשן
        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");

        // 1. יצירת תפריט הניווט (צד ימין/מרכז)
        HorizontalLayout navigation = new HorizontalLayout();
        Span logo = new Span("SmartCart");
        logo.getStyle()
                .set("font-size", "30px") // הגודל שביקשת!
                .set("font-weight", "bold")
                .set("color", "#2563eb") // הצבע הכחול של הלוגו שלך
                .set("margin-left", "20px") // קצת רווח מהתפריט
                .set("cursor", "pointer"); // סמן עכבר של לחיצה
        navigation.add(logo);
        // הוספת לוגו טקסטואלי מעוצב
        navigation.add(createNavButton("בית", VaadinIcon.HOME, ""));
        navigation.add(createNavButton("חנות", VaadinIcon.SHOP, "shop"));

        // הצגת אופציות נוספות רק למשתמשים מחוברים
        if (currentUser != null) 
        {
            navigation.add(createNavButton("סל חכם", VaadinIcon.MAGIC, "smart-cart"));
            
            // הצגת ממשק ניהול רק למשתמשים בעלי תפקיד אדמין
            if (currentUser.getRole() == Role.ADMIN) 
            {
                navigation.add(createNavButton("ניהול אתר", VaadinIcon.PACKAGE, "admin"));
            }
        }

        // 2. יצירת אזור המשתמש (צד שמאל) - כולל סל קניות ופרופיל
        HorizontalLayout userArea = new HorizontalLayout();
        userArea.setAlignItems(FlexComponent.Alignment.CENTER);

        if (currentUser != null) 
        {
            // אם המשתמש מחובר: הצג אייקון סל ותפריט פרופיל נפתח
            userArea.add(createCartIcon(), createProfileMenu(currentUser));
        } 
        else 
        {
            // אם המשתמש אורח: הצג כפתור התחברות
            com.vaadin.flow.component.button.Button loginBtn = new com.vaadin.flow.component.button.Button("התחבר", 
                    e -> UI.getCurrent().navigate("login"));
            loginBtn.addThemeVariants(com.vaadin.flow.component.button.ButtonVariant.LUMO_PRIMARY);
            userArea.add(loginBtn);
        }

        // 3. הוספת תתי-המיכלים למיכל הראשי וסידור המרווחים
        headerContainer.add(navigation, userArea);
        headerContainer.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerContainer.expand(navigation); // גורם לניווט לתפוס את מרב המקום ולדחוף את אזור המשתמש לקצה

        // שמירת מופע ה-Layout הנוכחי בתוך ה-UI כדי לאפשר גישה אליו מרכיבים אחרים (לעדכון הסל)
        ComponentUtil.setData(UI.getCurrent(), MainLayout.class, this);
    }

    /**
     * יצירת תפריט הפרופיל הנפתח (Dropdown) הכולל אוואטר ואינדיקטור מחובר.
     */
    private Div createProfileMenu(User user) 
    {
        // יצירת האוואטר עם שם המשתמש
        Avatar avatar = new Avatar(user.getFullName());
        avatar.getStyle().set("cursor", "pointer");

        // יצירת הנקודה הירוקה המציינת מצב "מחובר" (Online Indicator)
        Div onlineDot = new Div();
        onlineDot.getStyle()
                .set("width", "12px")
                .set("height", "12px")
                .set("background-color", "#22c55e")
                .set("border", "2px solid white")
                .set("border-radius", "50%")
                .set("position", "absolute")
                .set("bottom", "0")
                .set("right", "0")
                .set("z-index", "1");

        // מיכל לאוואטר ולנקודה במיקום יחסי
        Div avatarContainer = new Div(avatar, onlineDot);
        avatarContainer.getStyle().set("position", "relative").set("display", "inline-block");

        // יצירת תפריט ה-MenuBar שייפתח בלחיצה על האוואטר
        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        
        MenuItem menuItem = menuBar.addItem(avatarContainer);
        
        // אופציה 1 בתפריט: היסטוריית הזמנות
        MenuItem historyItem = menuItem.getSubMenu().addItem("היסטוריית הזמנות", 
                e -> UI.getCurrent().navigate("history"));
        historyItem.addComponentAsFirst(VaadinIcon.TIME_BACKWARD.create());

        // אופציה 2 בתפריט: החלפת חשבון (מנתק ושולח ל-Login)
        MenuItem switchAccountItem = menuItem.getSubMenu().addItem("החלף חשבון", e -> 
        {
            VaadinSession.getCurrent().setAttribute("user", null);
            UI.getCurrent().navigate("login");
        });
        switchAccountItem.addComponentAsFirst(VaadinIcon.USER_CARD.create());

        // אופציה 3 בתפריט: התנתקות מלאה וחזרה לדף הבית כאורח
        MenuItem logoutItem = menuItem.getSubMenu().addItem("התנתק", e -> 
        {
            VaadinSession.getCurrent().setAttribute("user", null);
            UI.getCurrent().getPage().setLocation("/"); 
        });
        logoutItem.addComponentAsFirst(VaadinIcon.SIGN_OUT.create());

        return new Div(menuBar);
    }

    /**
     * יצירת אייקון סל הקניות עם ה-Badge (המספר האדום) המציג את כמות הפריטים.
     */
    private HorizontalLayout createCartIcon() 
    {
        // שליפת רשימת הסל מהסשן לחישוב הכמות
        List<Product> cart = (List<Product>) VaadinSession.getCurrent().getAttribute("cart");
        int count = (cart != null) ? cart.size() : 0;

        Icon icon = VaadinIcon.CART.create();
        icon.setSize("26px");

        // יצירת ה-Badge ושמירה שלו כמשתנה מחלקה לצורך עדכון עתידי ללא רענון דף
        cartBadge = new Span(String.valueOf(count));
        cartBadge.getStyle()
                .set("background-color", "#ef4444")
                .set("color", "white")
                .set("font-size", "11px")
                .set("font-weight", "bold")
                .set("border-radius", "50%")
                .set("width", "18px")
                .set("height", "18px")
                .set("display", count > 0 ? "flex" : "none") // הסתרה אם הסל ריק
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("position", "absolute")
                .set("top", "-8px")
                .set("right", "-8px");

        // מיכל לאייקון ולבאדג' במיקום יחסי
        Div container = new Div(icon, cartBadge);
        container.getStyle().set("position", "relative").set("cursor", "pointer");
        
        // לחיצה על הסל מעבירה לדף ניהול הסל
        container.addClickListener(e -> UI.getCurrent().navigate("cart"));

        return new HorizontalLayout(container);
    }

    /**
     * פונקציה ציבורית לעדכון מספר הפריטים בסל בזמן אמת.
     * נקראת בדרך כלל ממחלקות אחרות (כמו ProductCard) לאחר הוספת מוצר.
     */
    public void refreshCartCount() 
    {
        List<Product> cart = (List<Product>) VaadinSession.getCurrent().getAttribute("cart");
        int count = (cart != null) ? cart.size() : 0;
        
        if (cartBadge != null) 
        {
            // עדכון הטקסט והנראות של ה-Badge ללא טעינת הדף מחדש
            cartBadge.setText(String.valueOf(count));
            cartBadge.getStyle().set("display", count > 0 ? "flex" : "none");
        }
    }

    /**
     * פונקציית עזר ליצירת כפתורי ניווט מעוצבים.
     */
    private com.vaadin.flow.component.button.Button createNavButton(String text, VaadinIcon icon, String route) 
    {
        return new com.vaadin.flow.component.button.Button(text, icon.create(), 
                e -> UI.getCurrent().navigate(route));
    }

}