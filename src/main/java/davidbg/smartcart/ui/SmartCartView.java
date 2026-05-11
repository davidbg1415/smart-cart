package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.SurveyCriteria;
import davidbg.smartcart.services.*;

import java.util.List;

@Route("smart-cart")
public class SmartCartView extends VerticalLayout 
{
    private final SmartCartAlgoService dpService;
    private final ProductService productService;
    private final ScoringService scoringService; 

    // רכיבי השאלון
    private Select<String> gender = new Select<>();
    private Select<String> category = new Select<>();
    private Select<String> occasion = new Select<>();
    private Select<String> style = new Select<>();
    private Select<String> fabric = new Select<>();
    private Select<String> color = new Select<>();
    private Select<String> season = new Select<>();
    private Select<String> pattern = new Select<>();

    // רכיבי אילוצי ה-DP
    private NumberField budget = new NumberField("תקציב (₪)");
    private IntegerField minItems = new IntegerField("מינימום פריטים");
    private IntegerField maxItems = new IntegerField("מקסימום פריטים");
    
    private Grid<Product> grid = new Grid<>(Product.class);
    private Span summary = new Span();

    public SmartCartView(SmartCartAlgoService dpService, ProductService productService, 
                        ScoringService scoringService, UserService userService) 
    {
        this.dpService = dpService;
        this.productService = productService;
        this.scoringService = scoringService;

        setAlignItems(Alignment.CENTER);
        add(new H2("בונה החבילות החכם - שאלון סטייל"));

        setupSurveyFields();

        // סידור השאלון בשורות
        HorizontalLayout row1 = new HorizontalLayout(gender, category, occasion, season);
        HorizontalLayout row2 = new HorizontalLayout(style, fabric, color, pattern);
        HorizontalLayout row3 = new HorizontalLayout(budget, minItems, maxItems);
        
        Button calculate = new Button("חשב סל אופטימלי", e -> runDP());
        calculate.getStyle().set("background-color", "#28a745").set("color", "white").set("padding", "10px 20px");

        // הגדרת טבלה
        grid.setColumns("name");
        grid.addColumn(p -> String.format("%.2f ₪", p.getPrice() / 10.0)).setHeader("מחיר");
        grid.addColumn(p -> String.format("%.1f%%", p.getTemporaryScore())).setHeader("רמת התאמה");

        add(row1, row2, row3, calculate, summary, grid);
    }

    private void setupSurveyFields() {
        gender.setLabel("עבור מי?");
        gender.setItems("גברים", "נשים");
        
        category.setLabel("מה מחפשים?");
        category.setItems("הכל", "חולצות", "מכנסיים", "נעליים","מעילים","חליפות");

        occasion.setLabel("אירוע");
        occasion.setItems("Wedding", "Office", "Gym", "Casual", "Vacation", "Night-Out");

        style.setLabel("סגנון");
        style.setItems("Elegant", "Streetwear", "Minimalist", "Oversize", "Classic");

        fabric.setLabel("סוג בד");
        fabric.setItems("100% כותנה", "100% פשתן", "סאטן", "צמר מרינו", "דנים");

        color.setLabel("צבע");
        color.setItems("שחור", "לבן", "כחול נייבי", "בז'", "אפור", "ירוק זית");

        season.setLabel("עונה");
        season.setItems("Summer", "Winter", "Spring-Essentials", "Autumn");

        pattern.setLabel("דוגמה");
        pattern.setItems("חלק", "משובץ", "פסים", "פרחוני","דגמח");

        budget.setPlaceholder("למשל 500");
        minItems.setValue(1);
        maxItems.setValue(3);
    }

    private void runDP() 
    {
        if (budget.getValue() == null) return;

        // 1. יצירת אובייקט הקריטריונים מהשאלון
        SurveyCriteria criteria = new SurveyCriteria();
        criteria.gender = gender.getValue();
        criteria.category = category.getValue();
        criteria.occasion = occasion.getValue();
        criteria.style = style.getValue();
        criteria.fabric = fabric.getValue();
        criteria.color = color.getValue();
        criteria.season = season.getValue();
        criteria.pattern = pattern.getValue();

        // 2. קבלת כל המוצרים וביצוע ניקוד (Scoring) לפי השאלון
        List<Product> products = productService.getAllProducts();
        scoringService.calculateScoresBySurvey(products, criteria);

        // 3. הרצת ה-DP התלת-ממדי (המרה לעשרות אגורות)
        int budgetUnits = (int) Math.round(budget.getValue() * 10);
        List<Product> bundle = dpService.getOptimalBundle(products, budgetUnits, minItems.getValue(), maxItems.getValue());

        // 4. הצגה ועדכון סיכום
        grid.setItems(bundle);
        double totalCost = bundle.stream().mapToDouble(p -> p.getPrice() / 10.0).sum();
        double avgMatch = bundle.stream().mapToDouble(Product::getTemporaryScore).average().orElse(0);
        
        summary.setText(String.format("הסל האופטימלי: %d פריטים | עלות: %.2f ₪ | רמת התאמה ממוצעת: %.1f%%", bundle.size(), totalCost, avgMatch));
    }
}