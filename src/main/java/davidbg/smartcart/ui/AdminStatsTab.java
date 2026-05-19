package davidbg.smartcart.ui;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.datamodels.OrderItem;
import davidbg.smartcart.services.OrderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * טאב סטטיסטיקה ונתונים יבשים - מציג מוצרים מובילים וגרפים ויזואליים של מכירות.
 */
public class AdminStatsTab extends VerticalLayout 
{
    public AdminStatsTab(OrderService orderService) 
    {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        List<Order> orders = orderService.getAllOrders();

        // 1. חישוב נתונים יבשים בסיסיים
        int totalOrders = orders.size();
        long totalItemsSold = orders.stream()
                .filter(o -> o.getItems() != null)
                .mapToLong(o -> o.getItems().size())
                .sum();

        // קופסאות נתונים עליונות (Cards)
        HorizontalLayout cardsLayout = new HorizontalLayout();
        cardsLayout.setWidthFull();
        cardsLayout.add(
            createStatCard("סך הכל הזמנות", String.valueOf(totalOrders), "#3b82f6"),
            createStatCard("פריטים שנמכרו", String.valueOf(totalItemsSold), "#a855f7")
        );

        // 2. חישוב המוצר הכי נמכר (אלגוריתם חלוקה וספירה מבוסס סטרים)
        Map<String, Long> productCounts = orders.stream()
                .filter(o -> o.getItems() != null)
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.counting()));

        // שליפת המוצר המוביל
        String topProduct = productCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("אין נתונים עדיין");

        long topProductCount = productCounts.getOrDefault(topProduct, 0L);
        cardsLayout.add(createStatCard("המוצר הכי נמכר", topProduct + " (" + topProductCount + " יח')", "#f59e0b"));

        // 3. יצירת גרף עמודות ויזואלי באמצעות ProgressBar לפילוח קטגוריות
        VerticalLayout chartLayout = new VerticalLayout();
        chartLayout.add(new H3("פילוח ויזואלי של פופולריות מוצרים (באחוזים)"));
        chartLayout.getStyle().set("background-color", "#f8fafc").set("border-radius", "10px").set("padding", "20px");

        if (totalItemsSold > 0) 
        {
            // נציג את 3 המוצרים הכי נמכרים כעמודות גרף
            productCounts.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(3)
                    .forEach(entry -> {
                        String productName = entry.getKey();
                        long count = entry.getValue();
                        double percentage = (double) count / totalItemsSold;

                        ProgressBar progressBar = new ProgressBar();
                        progressBar.setValue(percentage);
                        progressBar.setHeight("15px");

                        Span label = new Span(String.format("%s - %d יחידות (%.1f%%)", productName, count, percentage * 100));
                        label.getStyle().set("font-weight", "bold");

                        chartLayout.add(label, progressBar);
                    });
        } 
        else 
        {
            chartLayout.add(new Span("אין מספיק נתוני רכישה להצגת גרף."));
        }

        add(cardsLayout, chartLayout);
    }

    /**
     * פונקציית עזר ליצירת כרטיס מידע מעוצב (Card)
     */
    private VerticalLayout createStatCard(String title, String value, String color) 
    {
        VerticalLayout card = new VerticalLayout();
        card.setAlignItems(Alignment.CENTER);
        card.getStyle()
            .set("background-color", "#ffffff")
            .set("border", "1px solid #e2e8f0")
            .set("border-radius", "8px")
            .set("padding", "15px")
            .set("box-shadow", "0 1px 3px rgba(0,0,0,0.05)");

        Span titleSpan = new Span(title);
        titleSpan.getStyle().set("color", "#64748b").set("font-size", "14px");

        Span valueSpan = new Span(value);
        valueSpan.getStyle().set("color", color).set("font-size", "22px").set("font-weight", "bold");

        card.add(titleSpan, valueSpan);
        return card;
    }
}