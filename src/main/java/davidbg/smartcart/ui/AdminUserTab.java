package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.UserService;

/**
 * כרטיסיית ניהול משתמשים - מאפשרת לאדמין לצפות ברשומים ולמחוק משתמשים.
 */
public class AdminUserTab extends VerticalLayout 
{
    private final UserService userService;
    private final Grid<User> userGrid = new Grid<>(User.class, false);

    public AdminUserTab(UserService userService) 
    {
        this.userService = userService;
        
        setSizeFull();
        setPadding(true);

        H3 title = new H3("ניהול משתמשים במערכת");

        // הגדרת הטבלה ורענון הנתונים
        configureUserGrid();
        refreshUsers();

        add(title, userGrid);
    }

    private void configureUserGrid() 
    {
        // הוספת עמודות לפי השדות במודל המשתמש שלך
        userGrid.addColumn(User::getFullName).setHeader("שם פרטי + שם משפחה").setSortable(true);
        userGrid.addColumn(User::getEmail).setHeader("אימייל").setAutoWidth(true);
        userGrid.addColumn(User::getRole).setHeader("הרשאה / תפקיד");

        // עמודת פעולות - כפתור מחיקה
        userGrid.addComponentColumn(user -> 
        {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);

            // בדיקת הגנה: שליפת המשתמש המחובר כרגע מהסשן
            User loggedInUser = (User) VaadinSession.getCurrent().getAttribute("user");
            
            // חסימת האפשרות של האדמין למחוק את עצמו
            if (loggedInUser != null && loggedInUser.getId().equals(user.getId())) 
            {
                deleteBtn.setEnabled(false);
            }

            deleteBtn.addClickListener(e -> 
            {
                try 
                {
                    userService.deleteUser(user);
                    Notification.show("המשתמש " + user.getFullName() + " נמחק בהצלחה");
                    refreshUsers(); // רענון הגריד מיד לאחר המחיקה
                } 
                catch (Exception ex) 
                {
                    Notification.show("שגיאה במחיקת משתמש: " + ex.getMessage());
                }
            });

            return deleteBtn;
        }).setHeader("פעולות");
    }

    private void refreshUsers() 
    {
        userGrid.setItems(userService.getAllUsers());
    }
}