package davidbg.smartcart.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.datamodels.OrderItem;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.OrderService;


import java.time.format.DateTimeFormatter;
import java.util.List;

@Route(value = "history", layout = MainLayout.class)
@PageTitle("היסטוריה | SmartCart")
public class HistoryView extends VerticalLayout implements BeforeEnterObserver
{

    public HistoryView(OrderService orderService) 
    {
        setAlignItems(Alignment.CENTER);
        setPadding(true);

        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");
        if (currentUser == null) 
        {
            add(new H2("אנא התחבר כדי לצפות בהיסטוריה."));
            return;
        }

        add(new H1("היסטוריית הזמנות"));

        List<Order> orders = orderService.getOrdersByUserId(currentUser.getId());

        if (orders.isEmpty()) 
        {
            add(new Span("לא נמצאו הזמנות קודמות."));
        } 
        else 
        {
            Grid<Order> grid = new Grid<>(Order.class, false);
            
            // עמודת תאריך
            grid.addColumn(order -> order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .setHeader("תאריך")
                .setAutoWidth(true);

            // עמודת מחיר סופי
            grid.addColumn(order -> String.format("%.2f ₪", order.getTotalPrice()))
                .setHeader("מחיר");

            // עמודה מיוחדת המציגה את רשימת המוצרים (עם תמונות ושמות)
            grid.addColumn(new ComponentRenderer<>(order -> 
            {
                HorizontalLayout itemsLayout = new HorizontalLayout();
                itemsLayout.setSpacing(true);
                
                if (order.getItems() != null) 
                {
                    for (OrderItem item : order.getItems()) 
                    {
                        // יצירת תמונה קטנה לכל מוצר
                        Image img = new Image(item.getImageUrl(), "");
                        img.setWidth("40px");
                        img.setHeight("40px");
                        img.getStyle().set("border-radius", "4px");
                        
                        // הוספת ה-Tooltip (שם המוצר כשמרחפים עם העכבר)
                        img.setTitle(item.getProductName()); 
                        
                        itemsLayout.add(img);
                    }
                }
                return itemsLayout;
            })).setHeader("מוצרים שנרכשו");

            grid.setItems(orders);
            grid.setWidthFull();
            grid.setMaxWidth("1000px");
            grid.setAllRowsVisible(true); // מציג את כל השורות ללא גלילה פנימית של הטבלה

            add(grid);
        }
    }

    @Override
public void beforeEnter(BeforeEnterEvent event) {
    if (com.vaadin.flow.server.VaadinSession.getCurrent().getAttribute("user") == null) {
        event.rerouteTo(""); // מעיף אוטומטית לדף הבית אם הוא לא מחובר ספציפית
    }
}
}