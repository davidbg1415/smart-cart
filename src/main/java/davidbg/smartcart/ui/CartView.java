package davidbg.smartcart.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * מחלקה המייצגת את דף סל הקניות.
 * מאפשרת צפייה בפריטים, הסרתם וביצוע תהליך תשלום סימולטיבי.
 */
@Route(value = "cart", layout = MainLayout.class)
@PageTitle("סל הקניות | SmartCart")
public class CartView extends VerticalLayout implements BeforeEnterObserver
{

    private final OrderService orderService;

    /**
     * קונסטרקטור הדף - בונה את ממשק הסל על בסיס הנתונים בסשן.
     */
    public CartView(OrderService orderService) 
    {
        this.orderService = orderService;

        // הגדרת יישור מרכזי לכל תוכן הדף
        setAlignItems(Alignment.CENTER);
        
        // הוספת ריווח פנימי לדף
        setPadding(true);

        // שליפת רשימת המוצרים מתוך הסשן של המשתמש
        List<Product> cart = (List<Product>) VaadinSession.getCurrent().getAttribute("cart");

        // בדיקה: אם הסל ריק או לא קיים, נציג הודעה מתאימה וכפתור חזרה
        if (cart == null || cart.isEmpty()) 
        {
            // הוספת כותרת המודיעה שהסל ריק
            add(new H2("הסל שלך ריק..."));
            
            // הוספת כפתור ניווט חזרה לדף החנות
            add(new Button("חזור לחנות", e -> UI.getCurrent().navigate("shop")));
            
            // עצירת המשך בניית הדף
            return;
        }

        // יצירת כותרת ראשית לדף
        H1 title = new H1("סל הקניות שלי");
        
        // יצירת מיכל אנכי שיכיל את כל שורות המוצרים
        VerticalLayout listLayout = new VerticalLayout();
        
        // פריסת המיכל לכל הרוחב עם הגבלה ל-800 פיקסלים למראה נקי
        listLayout.setWidthFull();
        listLayout.setMaxWidth("800px");

        // משתנה לצבירת הסכום הכולל לתשלום
        double total = 0;
        
        // מעבר בלולאה על כל המוצרים בסל
        for (Product p : cart) 
        {
            // הוספת מחיר המוצר (חלקי 10) לסכום הכולל
            total += p.getPrice() / 10.0;
            
            // יצירת שורה ויזואלית עבור המוצר והוספתה למיכל
            listLayout.add(createItemRow(p, cart));
        }

        // יצירת תווית המציגה את הסכום הסופי בפורמט מטבע שקלי
        Span totalLabel = new Span("סה\"כ לתשלום: " + String.format("%.2f ₪", total));
        
        // עיצוב תווית המחיר הסופי (גופן גדול ומודגש)
        totalLabel.getStyle().set("font-size", "1.8em")
                             .set("font-weight", "bold")
                             .set("margin-top", "20px");

        // יצירת כפתור לביצוע התשלום עם אייקון של כרטיס אשראי
        Button payBtn = new Button("מעבר לתשלום מאובטח", VaadinIcon.CREDIT_CARD.create(), e -> processPayment());
        
        // עיצוב כפתור התשלום (גודל וצבע ירוק)
        payBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        payBtn.getStyle().set("background-color", "#22c55e")
                        .set("margin-top", "20px");

        // הוספת כל הרכיבים שנבנו אל תוך דף ה-View
        add(title, listLayout, new Hr(), totalLabel, payBtn);
    }

    /**
     * פונקציה ליצירת שורה עבור מוצר בודד בסל.
     * @param p המוצר להצגה.
     * @param cart רשימת הסל המלאה לצורך ביצוע פעולות (כמו הסרה).
     * @return אובייקט HorizontalLayout המייצג את השורה.
     */
    private HorizontalLayout createItemRow(Product p, List<Product> cart) 
    {
        // יצירת תצוגת תמונה ממוזערת למוצר
        Image img = new Image(p.getImageUrl(), "");
        img.setWidth("50px");
        
        // הצגת שם המוצר ומחירו
        Span name = new Span(p.getName());
        Span price = new Span(String.format("%.2f ₪", p.getPrice() / 10.0));
        
        // יצירת כפתור הסרה (פח אשפה)
        Button remove = new Button(VaadinIcon.TRASH.create(), e -> 
        {
            // הסרת המוצר הספציפי מרשימת הסל
            cart.remove(p);
            
            // עדכון רשימת הסל החדשה בתוך ה-VaadinSession
            VaadinSession.getCurrent().setAttribute("cart", cart);
            
            // ריענון הדף כדי לעדכן את הסכום והתצוגה
            UI.getCurrent().getPage().reload();
        });
        
        // עיצוב כפתור ההסרה בצבע אדום ללא רקע
        remove.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);

        // יצירת השורה האופקית המאגדת את כל הרכיבים יחד
        HorizontalLayout row = new HorizontalLayout(img, name, price, remove);
        
        // הגדרות פריסה לשורה: רוחב מלא, יישור מרכזי ופיזור שווה
        row.setWidthFull();
        row.setAlignItems(Alignment.CENTER);
        row.setJustifyContentMode(JustifyContentMode.BETWEEN);
        
        // הוספת קו מפריד תחתון וריווח
        row.getStyle().set("border-bottom", "1px solid #eee").set("padding", "10px");
        
        return row;
    }

    /**
     * פונקציה המדמה תהליך תשלום מאובטח.
     * משתמשת ב-Thread נפרד כדי להציג ProgressBar בזמן המתנה.
     */

private void processPayment() 
{
    Dialog dialog = new Dialog();
    dialog.setCloseOnOutsideClick(false);
    
    VerticalLayout layout = new VerticalLayout();
    layout.setAlignItems(Alignment.CENTER);
    layout.add(new H3("מעבד תשלום..."), new ProgressBar() {{ setIndeterminate(true); }});
    
    dialog.add(layout);
    dialog.open();

    Runnable paymentTask = () -> 
    {
        try 
        {
            Thread.sleep(2000); // הדמיית זמן סליקה
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        getUI().ifPresent(ui -> ui.access(() -> 
        {
            // --- שלב א: הכנת הנתונים לשמירה ---
            User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");
            List<Product> cartProducts = (List<Product>) VaadinSession.getCurrent().getAttribute("cart");

            // ה-UI "טיפש" עכשיו! הוא רק קורא לסרוויס שיבנה את ההזמנה וישמור אותה
            Order newOrder = orderService.createOrderFromCart(currentUser.getId(), cartProducts);
            orderService.addOrderToDB(newOrder);

            // --- שלב ב: עדכון ממשק המשתמש ---
            dialog.removeAll();
            layout.removeAll();
            
            Icon check = VaadinIcon.CHECK_CIRCLE.create();
            check.setSize("60px");
            check.getStyle().set("color", "green");

            layout.add(check, 
                       new H2("התשלום בוצע בהצלחה!"),
                       new Span("ההזמנה שלך נשמרה בהיסטוריה."));

            // כפתור חזרה לבית (לא זורק ישר להיסטוריה)
            Button goHome = new Button("חזרה לבית", e -> 
            {
                VaadinSession.getCurrent().setAttribute("cart", new ArrayList<>());
                ui.navigate("");
                dialog.close();
            });

            // כפתור לצפייה בהיסטוריה (אם המשתמש ירצה)
            Button goHistory = new Button("לצפייה בהזמנה", e -> 
            {
                VaadinSession.getCurrent().setAttribute("cart", new ArrayList<>());
                ui.navigate("history");
                dialog.close();
            });
            goHistory.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            HorizontalLayout btnLayout = new HorizontalLayout(goHome, goHistory);
            layout.add(btnLayout);
            dialog.add(layout);
        }));
    };

    new Thread(paymentTask).start();
}

@Override
public void beforeEnter(BeforeEnterEvent event) {
    if (com.vaadin.flow.server.VaadinSession.getCurrent().getAttribute("user") == null) {
        event.rerouteTo(""); // מעיף אוטומטית לדף הבית אם הוא לא מחובר ספציפית
    }
}
}