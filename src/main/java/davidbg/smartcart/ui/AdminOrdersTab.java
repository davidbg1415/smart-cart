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
 * טאב ניהולי (Admin Panel) המציג ניתוח פיננסי והיסטוריית רכישות.
 * מחלקה זו מממשת את עיקרון ה-Separation of Concerns:
 * ה-UI אחראי על הצגת הנתונים, בעוד שהעיבוד הלוגי והחישובים מבוצעים ב-OrderService.
 * מבנה המחלקה תומך בתצוגה אינטואיטיבית למנהל המערכת.
 * @author DAVID BEN GIGI
 */
public class AdminOrdersTab extends VerticalLayout 
{
    private final OrderService orderService;
    private final Grid<Order> orderGrid = new Grid<>(Order.class, false);
    private final Grid<MonthRevenueRow> monthlyGrid = new Grid<>();

    /**
     * בנאי המחלקה: מאתחל את הממשק, מפעיל את הזרקת התלויות ומבצע את משיכת הנתונים.
     * @param orderService שירות ניהול ההזמנות לצורך שליפת נתונים סטטיסטיים.
     */
    public AdminOrdersTab(OrderService orderService) 
    {
        this.orderService = orderService;
        setSizeFull();
        setPadding(true);

        List<Order> allOrders = orderService.getAllOrders();
        
        // --- 1. בניית תיבת הסיכום הפיננסי ---
        // שליפת סך ההכנסות משכבת השירות למניעת שכפול לוגיקה ב-UI
        double totalRevenue = orderService.calculateTotalRevenue();

        H2 revenueTitle = new H2(String.format("%.2f ₪", totalRevenue));
        revenueTitle.getStyle().set("color", "#22c55e").set("margin", "0");
        
        // יצירת קונטיינר מעוצב לנתון המרכזי
        VerticalLayout revenueBox = new VerticalLayout(new Span("סה\"כ הכנסות באתר:"), revenueTitle);
        revenueBox.setAlignItems(Alignment.CENTER);
        revenueBox.getStyle()
            .set("background-color", "#f0fdf4")
            .set("border-radius", "10px")
            .set("padding", "15px")
            .set("width", "300px")
            .set("box-shadow", "0 2px 5px rgba(0,0,0,0.1)");

        // --- 2. בניית הטבלאות והסידור הויזואלי ---
        setupOrderGrid();
        setupMonthlyGrid();

        // שימוש ב-HorizontalLayout ליצירת פריסת "דאשבורד" - צד אחד לניתוח, צד אחד לרשימה
        HorizontalLayout tablesLayout = new HorizontalLayout(monthlyGrid, orderGrid);
        tablesLayout.setSizeFull();
        // הגדרת משקולות (FlexGrow) כדי שטבלת ההזמנות תתפוס יותר שטח מאשר טבלת הסיכום החודשי
        tablesLayout.setFlexGrow(1, monthlyGrid); 
        tablesLayout.setFlexGrow(2, orderGrid);

        add(revenueBox, new H3("ניתוח פיננסי והיסטוריית עסקאות"), tablesLayout);
        
        // טעינת הנתונים לתוך ה-Grid הראשי
        orderGrid.setItems(allOrders);
    }

    /**
     * הגדרת העמודות של טבלת ההזמנות.
     * נעשה שימוש ב-Lambda Expressions למיפוי הנתונים בצורה בטוחה וקריאה.
     */
    private void setupOrderGrid() 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        orderGrid.addColumn(Order::getId).setHeader("מספר הזמנה").setAutoWidth(true);
        
        // הוספת עמודה מותאמת אישית המשתמשת ב-Formatter לתצוגת תאריך מובנת
        orderGrid.addColumn(order -> order.getOrderDate() != null ? order.getOrderDate().format(formatter) : "")
                 .setHeader("תאריך ושעה").setSortable(true);
        
        orderGrid.addColumn(order -> String.format("%.2f ₪", order.getTotalPrice()))
                 .setHeader("סכום העסקה").setSortable(true);
        
        // חישוב דינמי של כמות פריטים ישירות בתוך הטבלה
        orderGrid.addColumn(order -> order.getItems() != null ? order.getItems().size() : 0)
                 .setHeader("פריטים בסל");
    }

    /**
     * לוגיקה לקיבוץ וחישוב רווחים לפי חודשים.
     * השיטה מבצעת "מניפולציה בנתונים" (Data Transformation) כדי להציג מידע מופשט ונוח לקריאה.
     */
    private void setupMonthlyGrid() 
    {
        monthlyGrid.addColumn(MonthRevenueRow::getMonth).setHeader("חודש / שנה");
        monthlyGrid.addColumn(row -> String.format("%.2f ₪", row.getRevenue())).setHeader("סך הכל רווח");

        // משיכת נתונים מהשירות - מפה המקשרת תאריך (חודש) לסכום רווח מצטבר
        Map<String, Double> revenueByMonth = orderService.getMonthlyRevenue();

        // המרה לרשימה של אובייקטי MonthRevenueRow לשם תצוגה בטבלה
        List<MonthRevenueRow> monthlyRows = new ArrayList<>();
        revenueByMonth.forEach((month, revenue) -> monthlyRows.add(new MonthRevenueRow(month, revenue)));

        // מיון הפוך כדי שהחודשים האחרונים יופיעו בראש הטבלה
        monthlyRows.sort((r1, r2) -> r2.getMonth().compareTo(r1.getMonth()));

        monthlyGrid.setItems(monthlyRows);
        monthlyGrid.setWidth("350px");
    }

    /**
     * מחלקת עזר (Data Transfer Object) להצגת הנתונים ב-Grid.
     * הפרדה זו מאפשרת לנו לעצב נתונים בצורה פשוטה ללא השפעה על אובייקט ה-Order המקורי.
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