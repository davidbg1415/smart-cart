package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.User;
import davidbg.smartcart.services.ProductService;
import davidbg.smartcart.services.UserService;
import davidbg.smartcart.services.WeatherService;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherRecommendationSection extends VerticalLayout 
{
    private final WeatherService weatherService;
    private final ProductService productService;
    private final UserService userService;
    
    private final HorizontalLayout productsLayout = new HorizontalLayout();
    private final VerticalLayout missingCityLayout = new VerticalLayout();
    private final Span infoLabel = new Span();

    public WeatherRecommendationSection(WeatherService weatherService, ProductService productService, UserService userService) 
    {
        this.weatherService = weatherService;
        this.productService = productService;
        this.userService = userService;

        setPadding(true);
        setSpacing(true);
        setWidthFull();
        setMaxWidth("950px"); // הקטנת רוחב הרכיב הכללי
        
        // עיצוב קומפקטי
        getStyle().set("background-color", "#f8fafc")
                  .set("border-radius", "12px")
                  .set("border", "1px solid #e2e8f0")
                  .set("padding", "15px");

        H4 title = new H4("מערכת המלצות לבוש מותאמת מזג אוויר 🌤️");
        title.getStyle().set("margin", "0 0 10px 0").set("color", "#0f172a");

        productsLayout.setWidthFull();
        productsLayout.setSpacing(true);
        productsLayout.getStyle().set("justify-content", "center");

        setupMissingCityForm();

        add(title, infoLabel, productsLayout, missingCityLayout);
    }

    private void setupMissingCityForm() 
    {
        missingCityLayout.setAlignItems(Alignment.CENTER);
        missingCityLayout.setSpacing(true);
        missingCityLayout.setVisible(false);

        Span errorMsg = new Span("לא הגדרת עיר מגורים בחשבונך. אנא עדכן עיר על מנת לקבל המלצות לבוש:");
        errorMsg.getStyle().set("color", "#dc2626").set("font-weight", "bold").set("font-size", "14px");

        TextField cityInput = new TextField("עיר (באנגלית)");
        cityInput.setPlaceholder("e.g. Jerusalem, Eilat");
        
        Button saveCityBtn = new Button("עדכן עיר והצג המלצות", e -> {
            String enteredCity = cityInput.getValue();
            if (enteredCity != null && !enteredCity.trim().isEmpty()) {
                User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");
                if (currentUser != null) {
                    currentUser.setCity(enteredCity.trim());
                    userService.updateUser(currentUser); // שמירה ב-DB של המשתמש
                    VaadinSession.getCurrent().setAttribute("user", currentUser); // עדכון הסשן
                    
                    // רענון והצגת המלצות
                    loadRecommendations(enteredCity.trim());
                }
            } else {
                Notification.show("נא להזין שם עיר תקין");
            }
        });
        saveCityBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout form = new HorizontalLayout(cityInput, saveCityBtn);
        form.setAlignItems(Alignment.END);

        missingCityLayout.add(errorMsg, form);
    }

    public void loadRecommendations(String city) 
    {
        productsLayout.removeAll();
        
        // אם אין עיר - נציג את טופס העדכון ונצא מהמתודה
        if (city == null || city.trim().isEmpty()) {
            infoLabel.setVisible(false);
            productsLayout.setVisible(false);
            missingCityLayout.setVisible(true);
            return;
        }

        // אם יש עיר - נציג את התוכן הרגיל ונעלים את הטופס
        missingCityLayout.setVisible(false);
        infoLabel.setVisible(true);
        productsLayout.setVisible(true);

        double temp = weatherService.getTemperature(city);
        String englishTag = weatherService.getWeatherTag(temp);
        String hebrewTagName = weatherService.getWeatherHebrewName(englishTag);

        // 1. אומרים לדפדפן לרנדר את הרכיב הספציפי הזה מימין לשמאל
        infoLabel.getElement().setAttribute("dir", "rtl");

        // 2. מזינים את המחרוזת הרגילה שלך (בלי תגיות HTML ידניות)
        infoLabel.setText(String.format("הטמפרטורה ב-%s היא %.1f°C. הנה פריטי %s מומלצים:", city, temp, hebrewTagName));
        infoLabel.getStyle().set("font-weight", "500").set("color", "#475569").set("font-size", "14px");

        // סינון לפי התגית באנגלית (summer/winter) והגבלה ל-4 פריטים
        List<Product> matchingProducts = productService.getAllProducts().stream()
            .filter(p -> p.getTags() != null && p.getTags().contains(englishTag))
            .limit(4)
            .collect(Collectors.toList());

        if (matchingProducts.isEmpty()) {
            productsLayout.add(new Span("אין כרגע פריטים בקטלוג התואמים לתגית: " + englishTag));
            return;
        }

       for (Product p : matchingProducts) 
{
    // יצירת הכרטיס המקורי שלך במצב קומפקטי (true)
    ProductCard compactCard = new ProductCard(p, true);
    
    // דחיפה ישירות ללייאאוט של הוידג'ט
    productsLayout.add(compactCard);
}
           
        
    }
}