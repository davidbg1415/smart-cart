package davidbg.smartcart.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import davidbg.smartcart.datamodels.Role;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.UserService;
import davidbg.smartcart.utilities.MainLayout;

import java.util.ArrayList;

/**
 * מחלקת RegistrationView מנהלת את דף הרשמת המשתמשים החדשים למערכת.
 * הדף כולל טופס להזנת פרטים אישיים, אימות נתונים ושמירה במסד הנתונים דרך שירות המשתמשים.
 */
@Route(value = "register", layout = MainLayout.class) // הגדרת הנתיב כ-"register" תחת ה-Layout הראשי
@PageTitle("הרשמה | SmartCart")
public class RegistrationView extends VerticalLayout 
{

    private final UserService userService;

    /**
     * קונסטרקטור דף ההרשמה.
     * @param userService שירות המשתמשים המוזרק לטיפול בלוגיקת הרישום מול מסד הנתונים.
     */
    public RegistrationView(UserService userService) 
    {
        this.userService = userService;

        // 1. הגדרות עיצוב כלליות לדף (מרכוז ורקע מדורג)
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background", "linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%)");

        // 2. יצירת כותרת הדף
        H2 title = new H2("יצירת חשבון SmartCart חדש");
        title.getStyle().set("color", "#2c3e50");

        // 3. הגדרת שדות הקלט עבור טופס ההרשמה
        TextField fullName = new TextField("שם מלא");
        EmailField email = new EmailField("כתובת אימייל");
        PasswordField password = new PasswordField("סיסמה");
        PasswordField confirmPassword = new PasswordField("אימות סיסמה");
        TextField phone = new TextField("מספר טלפון");
        TextField city = new TextField(" עיר מגורים");

        // 4. סידור השדות בתוך FormLayout לקבלת מראה רספונסיבי (מותאם למסכים שונים)
        FormLayout formLayout = new FormLayout();
        formLayout.add(fullName, email, phone, city, password, confirmPassword);
        
        // הגדרת פריסת השדות: שדה אחד במסך קטן, שני שדות בשורה במסך רחב מ-500 פיקסלים
        formLayout.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("500px", 2)
        );

        // 5. יצירת כפתור ההרשמה והגדרת הלוגיקה שתופעל בעת לחיצה
        Button registerBtn = new Button("הירשם עכשיו", e -> 
        {
            // א. בדיקה בסיסית: האם הסיסמאות שהוזנו תואמות זו לזו
            if (!password.getValue().equals(confirmPassword.getValue())) 
            {
                Notification.show("הסיסמאות אינן תואמות", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // ב. יצירת אובייקט משתמש (User) חדש ואכלוסו בנתונים מהטופס
            User newUser = new User();
            newUser.setFullName(fullName.getValue());
            newUser.setEmail(email.getValue());
            newUser.setPassword(password.getValue()); // בשלב זה נשמר כטקסט חשוף (מומלץ להוסיף הצפנה בעתיד)
            newUser.setPhone(phone.getValue());
            
            // ג. קביעת ערכי ברירת מחדל למשתמש חדש (תפקיד משתמש רגיל ורשימת העדפות ריקה)
            newUser.setRole(Role.REGISTERED_USER);
            newUser.setPreferences(new ArrayList<>());

            // ד. פנייה לשירות המשתמשים לצורך ביצוע הרישום בפועל במסד הנתונים
            boolean success = userService.registerNewUser(newUser);

            if (success) 
            {
                // מקרה של הצלחה: הצגת הודעה וניווט המשתמש לדף ההתחברות
                Notification.show("החשבון נוצר בהצלחה!", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("login");
            } 
            else 
            {
                // מקרה של כישלון (למשל אם המייל כבר קיים במערכת)
                Notification.show("האימייל כבר קיים במערכת", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        // עיצוב ויזואלי לכפתור ההרשמה
        registerBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        registerBtn.setWidthFull();

        // 6. כפתור חזרה לדף ההתחברות עבור משתמשים שכבר רשומים
        Button backToLogin = new Button("כבר יש לכם חשבון? התחברו", e -> UI.getCurrent().navigate("login"));
        backToLogin.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // 7. יצירת כרטיס (Card) המרכז את כל רכיבי הטופס בעיצוב נקי
        VerticalLayout card = new VerticalLayout(title, formLayout, registerBtn, backToLogin);
        card.setWidth("auto");
        card.setMaxWidth("600px");
        card.setPadding(true);
        
        // הגדרת עיצוב הכרטיס הלבן עם צללית ופינות מעוגלות
        card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 4px 15px rgba(0,0,0,0.1)");
        
        // הוספת הכרטיס המלא למרכז המסך
        add(card);
    }
}