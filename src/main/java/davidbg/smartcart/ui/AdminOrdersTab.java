package davidbg.smartcart.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.services.OrderService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * טאב פיננסי המציג את כל הרכישות ומשקלל רווחים לפי חודשים.
 */
public class AdminOrdersTab extends VerticalLayout 
{
    private final OrderService orderService;
    private final Grid<Order> orderGrid = new Grid<>(Order.class, false);
    private final Grid<MonthRevenueRow> monthlyGrid = new Grid<>();

    public AdminOrdersTab(OrderService orderService) 
    {
        this.orderService = orderService;
        setSizeFull();
        setPadding(true);

        List<Order> allOrders = orderService.getAllOrders();
        
        // 1. חישוב רווח כולל (ברוטו) - הקריאה עברה ל-Service!
        double totalRevenue = orderService.calculateTotalRevenue();

        H2 revenueTitle = new H2(String.format("%.2f ₪", totalRevenue));
        revenueTitle.getStyle().set("color", "#22c55e").set("margin", "0");
        VerticalLayout revenueBox = new VerticalLayout(new Span("סה\"כ הכנסות באתר:"), revenueTitle);
        revenueBox.setAlignItems(Alignment.CENTER);
        revenueBox.getStyle().set("background-color", "#f0fdf4").set("border-radius", "10px").set("padding", "15px").set("width", "300px");

        // 2. הגדרת טבלאות
        setupOrderGrid();
        setupMonthlyGrid(); // שמתי לב שהורדתי את הפרמטר, כבר לא צריך לשלוח את הרשימה

        // סידור המסך: בצד אחד רווח חודשי, בצד שני רשימת ההזמנות
        HorizontalLayout tablesLayout = new HorizontalLayout(monthlyGrid, orderGrid);
        tablesLayout.setSizeFull();
        tablesLayout.setFlexGrow(1, monthlyGrid);
        tablesLayout.setFlexGrow(2, orderGrid);

        add(revenueBox, new H3("ניתוח פיננסי והיסטוריית עסקאות"), tablesLayout);
        
        orderGrid.setItems(allOrders);
    }

    private void setupOrderGrid() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        orderGrid.addColumn(Order::getId).setHeader("מספר הזמנה").setAutoWidth(true);
        orderGrid.addColumn(order -> order.getOrderDate() != null ? order.getOrderDate().format(formatter) : "").setHeader("תאריך ושעה").setSortable(true);
        orderGrid.addColumn(order -> String.format("%.2f ₪", order.getTotalPrice())).setHeader("סכום העסקה").setSortable(true);
        orderGrid.addColumn(order -> order.getItems() != null ? order.getItems().size() : 0).setHeader("פריטים בסל");
    }

    /**
     * לוגיקה לקיבוץ וחישוב רווחים לפי חודשים (נשאבת מה-Service)
     */
    private void setupMonthlyGrid() 
    {
        monthlyGrid.addColumn(MonthRevenueRow::getMonth).setHeader("חודש / שנה");
        monthlyGrid.addColumn(row -> String.format("%.2f ₪", row.getRevenue())).setHeader("סך הכל רווח");

        // ה-UI טיפש! הוא פשוט מבקש את המפה מה-Service:
        Map<String, Double> revenueByMonth = orderService.getMonthlyRevenue();

        // המרת המפה לרשימה עבור הטבלה
        List<MonthRevenueRow> monthlyRows = new ArrayList<>();
        revenueByMonth.forEach((month, revenue) -> monthlyRows.add(new MonthRevenueRow(month, revenue)));

        // מיון החודשים שיוצגו לפי הסדר
        monthlyRows.sort((r1, r2) -> r2.getMonth().compareTo(r1.getMonth()));

        monthlyGrid.setItems(monthlyRows);
        monthlyGrid.setWidth("350px");
    }

    /**
     * מחלקת עזר פנימית לייצוג שורת רווח חודשי.
     */
    public static class MonthRevenueRow 
    {
        private final String month;
        private final double revenue;

        public MonthRevenueRow(String month, double revenue) 
        {
            this.month = month;
            this.revenue = revenue;
        }

        public String getMonth() { return month; }
        public double getRevenue() { return revenue; }
    }
}