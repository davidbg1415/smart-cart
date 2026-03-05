package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.UserService;

/**
 * דף תצוגת משתמשים .
 * מציג טבלה של כל המשתמשים ואפשרות להוספת משתמש חדש.
 */
@Route("users") // http://localhost:8080/users
public class UserView extends VerticalLayout 
{

    private final UserService userService;
    private Grid<User> grid = new Grid<>(User.class);
    
    private TextField fullName = new TextField("שם מלא");
    private TextField email = new TextField("אימייל ");
    private TextField password = new TextField("סיסמה");
    private Button addButton = new Button("הוסף משתמש");

    public UserView(UserService userService) 
    {
        this.userService = userService;

        // הגדרת הטבלה
        grid.setColumns("fullName", "email", "password");
        updateGrid();

        //  הוספת משתמש
        addButton.addClickListener(e -> {
            User newUser = new User();
            newUser.setId(java.util.UUID.randomUUID().toString()); // יצירת מזהה זמני
            newUser.setFullName(fullName.getValue());
            newUser.setEmail(email.getValue());
            newUser.setPassword(password.getValue());
            
            userService.addUserToDB(newUser); //שמירה במסד הנתונים
            updateGrid(); // עדכון הטבלה
            clearForm(); // ניקוי שדות
        });

        // עיצוב הדף
        HorizontalLayout form = new HorizontalLayout(fullName, email, password, addButton);
        form.setVerticalComponentAlignment(Alignment.BASELINE, addButton);

        add(form, grid);
    }

    private void updateGrid() 
    {
        grid.setItems(userService.getAllUsers());
    }

    private void clearForm() 
    {
        fullName.clear();
        email.clear();
        password.clear();
    }
}