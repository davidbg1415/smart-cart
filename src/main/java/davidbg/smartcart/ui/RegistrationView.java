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

import java.util.ArrayList;

@Route("register")
@PageTitle("הרשמה | SmartCart")
public class RegistrationView extends VerticalLayout 
{

    private final UserService userService;

    public RegistrationView(UserService userService) 
    {
        this.userService = userService;

        // עיצוב הדף
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background", "linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%)");

        // כותרת
        H2 title = new H2("יצירת חשבון SmartCart חדש");
        title.getStyle().set("color", "#2c3e50");

        // יצירת שדות טופס ההרשמה 
        TextField fullName = new TextField("שם מלא");
        EmailField email = new EmailField("כתובת אימייל");
        PasswordField password = new PasswordField("סיסמה");
        PasswordField confirmPassword = new PasswordField("אימות סיסמה");
        TextField phone = new TextField("מספר טלפון");

        // סידור השדות בטופס
        FormLayout formLayout = new FormLayout();
        formLayout.add(fullName, email, phone, password, confirmPassword);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),new FormLayout.ResponsiveStep("500px", 2));

        // כפתור הרשמה
        Button registerBtn = new Button("הירשם עכשיו", e -> {
            // בדיקת התאמת סיסמאות 
            if (!password.getValue().equals(confirmPassword.getValue())) 
            {
                Notification.show("הסיסמאות אינן תואמות", 3000, Notification.Position.MIDDLE).addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // יצירת אובייקט משתמש חדש
            User newUser = new User();
            newUser.setFullName(fullName.getValue());
            newUser.setEmail(email.getValue());
            newUser.setPassword(password.getValue()); //   נוסיף הצפנה
            newUser.setPhone(phone.getValue());
            newUser.setRole(Role.REGISTERED_USER); // תפקיד ברירת מחדל 
            newUser.setPreferences(new ArrayList<>()); // רשימת העדפות משתמש ריקה להתחלה

            // ניסיון שמירה במסד הנתונים
            boolean success = userService.registerNewUser(newUser);

            if (success) 
            {
                Notification.show("החשבון נוצר בהצלחה!", 3000, Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("login");
            } else {
                Notification.show("האימייל כבר קיים במערכת", 3000, Notification.Position.MIDDLE).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        registerBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        registerBtn.setWidthFull();

        // כפתור חזרה להתחברות
        Button backToLogin = new Button("כבר יש לכם חשבון? התחברו", e -> UI.getCurrent().navigate("login"));
        backToLogin.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // ריכוז הכל בתוך קונטיינר מעוצב
        VerticalLayout card = new VerticalLayout(title, formLayout, registerBtn, backToLogin);
        card.setWidth("auto");
        card.setMaxWidth("600px");
        card.setPadding(true);
        card.getStyle().set("background-color", "white").set("border-radius", "15px").set("box-shadow", "0 4px 15px rgba(0,0,0,0.1)");
        add(card);
    }
}