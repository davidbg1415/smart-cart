package davidbg.smartcart.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * מחלקת LoginView אחראית על דף ההתחברות של המערכת.
 * הדף מאפשר למשתמשים קיימים להזדהות באמצעות אימייל וסיסמה.
 */
@Route(value = "login", layout = MainLayout.class) // הגדרת הנתיב כ-"login" תחת ה-Layout הראשי
@PageTitle("כניסה | SmartCart")
public class LoginView extends VerticalLayout 
{

    private final UserService userService;

    /**
     * קונסטרקטור דף ההתחברות.
     * @param userService שירות המשתמשים המוזרק (Dependency Injection) לטיפול באימות הנתונים.
     */
    public LoginView(UserService userService) 
    {
        this.userService = userService;

        // הגדרות עיצוב לדף ברמת העמוד כולו
        setSizeFull(); // פריסה על כל גובה ורוחב המסך
        setAlignItems(Alignment.CENTER); // מרכוז רכיבים אופקית
        setJustifyContentMode(JustifyContentMode.CENTER); // מרכוז רכיבים אנכית
        
        // הגדרת רקע מדורג (Gradient) לעיצוב יוקרתי
        getStyle().set("background", "linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)");

        // 1. יצירת כותרת הלוגו של דף הכניסה
        H1 logo = new H1("SmartCart 🛒");
        logo.getStyle().set("color", "#2c3e50").set("margin-bottom", "20px");

        // 2. הגדרת אובייקט ה-I18n (Internationalization) לצורך תרגום ממשק ה-Login לעברית
        LoginI18n i18n = LoginI18n.createDefault();
        
        // הגדרת הטקסטים בטופס (כותרות ושמות שדות)
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("כניסה למערכת");
        i18nForm.setUsername("אימייל");
        i18nForm.setPassword("סיסמה");
        i18nForm.setSubmit("התחבר");
        i18n.setForm(i18nForm);

        // הגדרת הודעת השגיאה שתופיע במקרה של פרטים לא נכונים
        LoginI18n.ErrorMessage i18nError = i18n.getErrorMessage();
        i18nError.setTitle("שגיאת התחברות");
        i18nError.setMessage("אימייל או סיסמה אינם נכונים. נסה שוב.");
        i18n.setErrorMessage(i18nError);

        // יצירת רכיב טופס ההתחברות והחלת התרגום עליו
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);
        loginForm.setForgotPasswordButtonVisible(true);

        // 3. הגדרת המאזין (Listener) לביצוע לוגיקת האימות בעת לחיצה על "התחבר"
        loginForm.addLoginListener(e -> 
        {
            // שליחת נתוני המשתמש לשירות האימות
            Optional<User> userOpt = userService.login(e.getUsername(), e.getPassword());

            if (userOpt.isPresent()) 
            {
                // מקרה של הצלחה: שליפת אובייקט המשתמש
                User user = userOpt.get();
                
                // שמירת אובייקט המשתמש בתוך ה-VaadinSession כדי שיהיה זמין לכל חלקי האפליקציה
                VaadinSession.getCurrent().setAttribute("user", user);
                List<Product> myCart = new ArrayList<>();
                VaadinSession.getCurrent().setAttribute("cart", myCart);
                
                // הצגת הודעת הצלחה צפה בחלק העליון של המסך
                Notification.show("ברוך הבא, " + user.getFullName() + "!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                
                // ניווט דפדפן חזרה לדף הבית
                UI.getCurrent().navigate(""); 
            } 
            else 
            {
                // מקרה של כישלון: הפעלת מצב שגיאה בטופס (מציג את הודעת ה-I18n שהגדרנו)
                loginForm.setError(true);
            }
        });

        // 4. יצירת אזור המעבר להרשמה עבור משתמשים חדשים
        Div registerLink = new Div();
        registerLink.getStyle().set("margin-top", "15px").set("color", "#555");
        registerLink.setText("עוד לא רשומים? ");
        
        // כפתור ניווט לדף ההרשמה (Register)
        Button signUpBtn = new Button("צרו חשבון עכשיו", event -> UI.getCurrent().navigate("register"));
        signUpBtn.getStyle().set("color", "#007bff").set("cursor", "pointer").set("padding", "0");
        signUpBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY); // עיצוב כפתור כטקסט לחיץ
        
        registerLink.add(signUpBtn);

        // 5. ריכוז כל הרכיבים בתוך מיכל (Container) מרכזי מעוצב
        VerticalLayout container = new VerticalLayout(logo, loginForm, registerLink);
        container.setAlignItems(Alignment.CENTER);
        container.setPadding(true);
        container.setSpacing(false);
        
        // עיצוב ה"כרטיס" הלבן שעליו יושב הטופס
        container.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.1)")
                .set("width", "auto")
                .set("max-width", "400px");

        // הוספת הכרטיס המלא למרכז הדף
        add(container);
    }
}