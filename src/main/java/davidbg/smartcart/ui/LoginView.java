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
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.UserService;

import java.util.Optional;

@Route("login")
@PageTitle("כניסה | SmartCart")
public class LoginView extends VerticalLayout 
{

    private final UserService userService;

    public LoginView(UserService userService) 
    {
        this.userService = userService;

        // הגדרות עיצוב כלליות למרכז הדף
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background", "linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)");

        // 1. הגדרת כותרת ועיצוב לוגו
        H1 logo = new H1("SmartCart 🛒");
        logo.getStyle().set("color", "#2c3e50").set("margin-bottom", "20px");

        // 2. התאמת ממשק ה-Login לעברית 
        LoginI18n i18n = LoginI18n.createDefault();
        
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("כניסה למערכת");
        i18nForm.setUsername("אימייל");
        i18nForm.setPassword("סיסמה");
        i18nForm.setSubmit("התחבר");
        i18nForm.setForgotPassword("שכחתי סיסמה");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nError = i18n.getErrorMessage();
        i18nError.setTitle("שגיאת התחברות");
        i18nError.setMessage("אימייל או סיסמה אינם נכונים. נסה שוב.");
        i18n.setErrorMessage(i18nError);

        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);
        loginForm.setForgotPasswordButtonVisible(true);

        // 3. לוגיקת האימות והניווט
        loginForm.addLoginListener(e -> 
        {
            Optional<User> userOpt = userService.login(e.getUsername(), e.getPassword());

            if (userOpt.isPresent()) 
            {
                User user = userOpt.get();
                
                // שמירת המשתמש המחובר ב-Session כדי שה-MainView יזהה אותו
                VaadinSession.getCurrent().setAttribute("user", user);
                Notification.show("ברוך הבא, " + user.getFullName() + "!", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                
                // ניווט חזרה לדף הבית - עכשיו הוא יוצג כ-Dashboard
                UI.getCurrent().navigate(""); 
            } 
            else 
            {
                loginForm.setError(true); // מציג את הודעת השגיאה בתוך הטופס
            }
        });

        // 4. קישור להרשמה (למשתמשים חדשים)
        Div registerLink = new Div();
        registerLink.getStyle().set("margin-top", "15px").set("color", "#555");
        registerLink.setText("עוד לא רשומים? ");
        
        Button signUpBtn = new Button("צרו חשבון עכשיו", event -> UI.getCurrent().navigate("register"));
        signUpBtn.getStyle().set("color", "#007bff").set("cursor", "pointer").set("padding", "0");
        signUpBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        registerLink.add(signUpBtn);

        // הוספת כל הרכיבים למסך
        VerticalLayout container = new VerticalLayout(logo, loginForm, registerLink);
        container.setAlignItems(Alignment.CENTER);
        container.setPadding(true);
        container.setSpacing(false);
        container.getStyle()
                .set("background-color", "white")
                .set("border-radius", "15px")
                .set("box-shadow", "0 10px 25px rgba(0,0,0,0.1)")
                .set("width", "auto")
                .set("max-width", "400px");

        add(container);
    }
}