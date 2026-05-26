package davidbg.smartcart.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import davidbg.smartcart.datamodels.Role;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.OrderService;
import davidbg.smartcart.services.ProductService;
import davidbg.smartcart.services.UserService;


/**
 * דף ניהול ראשי - Admin Dashboard.
 * מאפשר שליטה מלאה על מוצרים, משתמשים וצפייה בנתונים עסקיים.
 */
@Route(value = "admin", layout = MainLayout.class)
@PageTitle("ניהול מערכת | SmartCart")
public class AdminView extends VerticalLayout implements BeforeEnterObserver
{
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    private final Div contentContainer = new Div(); // המכולה שמתחלפת

    public AdminView(ProductService productService, UserService userService, OrderService orderService) 
    {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        H1 header = new H1("לוח בקרה למנהל");
        header.getStyle().set("color", "#1e293b");

        // יצירת הכרטיסיות
        Tab productsTab = new Tab("ניהול מוצרים");
        Tab usersTab = new Tab("ניהול משתמשים");
        Tab ordersTab = new Tab("הזמנות ורווחים");
        Tab statsTab = new Tab("סטטיסטיקה");

        Tabs tabs = new Tabs(productsTab, usersTab, ordersTab, statsTab);
        tabs.setWidthFull();

        // לוגיקת החלפת התוכן
        tabs.addSelectedChangeListener(event -> 
        {
            updateContent(event.getSelectedTab(), productsTab, usersTab, ordersTab, statsTab);
        });

        contentContainer.setSizeFull();
        
        // הגדרת תוכן ראשוני
        updateContent(productsTab, productsTab, usersTab, ordersTab, statsTab);

        add(header, tabs, contentContainer);
    }

    private void updateContent(Tab selectedTab, Tab products, Tab users, Tab orders, Tab stats) 
    {
        contentContainer.removeAll();

        if (selectedTab.equals(products)) 
        {
            contentContainer.add(new AdminProductTab(productService));  
        } 
        else if (selectedTab.equals(users)) 
        {
           contentContainer.add(new AdminUserTab(userService));
        }

        else if (selectedTab.equals(orders)) 
        {
           contentContainer.add(new AdminOrdersTab(orderService));
        }

        else if (selectedTab.equals(stats)) 
        {
            contentContainer.add(new AdminStatsTab(orderService));
        }
       
    }

    @Override
public void beforeEnter(BeforeEnterEvent event) 
{
    // 1. שליפה וביצוע Casting ל-User
    User currentUser = (User) com.vaadin.flow.server.VaadinSession.getCurrent().getAttribute("user");

    // 2. עכשיו השדה getRole() מוכר ורלוונטי
    if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
    event.rerouteTo(""); 
}
}
}