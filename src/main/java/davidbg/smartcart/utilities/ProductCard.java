package davidbg.smartcart.utilities;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.datamodels.User;
import java.util.ArrayList;
import java.util.List;

/**
 * כרטיס מוצר משודרג המציג תמונה, שם, תיאור והרכב בד.
 * תומך כעת גם במצב קומפקטי עבור מערכת ההמלצות.
 */
public class ProductCard extends VerticalLayout 
{
    // הקונסטרקטור המקורי שלך - קורא לקונסטרקטור החדש עם compact = false (בשביל החנות הרגילה)
    public ProductCard(Product product) 
    {
        this(product, false);
    }

    // הקונסטרקטור החדש והמשודרג - מקבל פרמטר שאומר לו האם להיות קטן
    public ProductCard(Product product, boolean compact) 
    {
        // הגדרות מימדים דינמיות לפי המצב
        setWidth(compact ? "170px" : "260px");
        setHeight(compact ? "340px" : "520px");
        
        setPadding(true);
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.BETWEEN);

        // עיצוב ה-CSS של הכרטיס
        getStyle().set("overflow", "hidden")
                  .set("border", "1px solid #e2e8f0")
                  .set("border-radius", "15px")
                  .set("background-color", "white")
                  .set("box-shadow", "0 4px 12px rgba(0,0,0,0.08)");

        // 1. מיכל התמונה - משתנה בהתאם למצב
        Div imageContainer = new Div();
        imageContainer.setWidthFull();
        imageContainer.setHeight(compact ? "100px" : "200px"); // תמונה קטנה יותר בקומפקטי
        imageContainer.getStyle().set("display", "flex").set("justify-content", "center").set("align-items", "center").set("overflow", "hidden");

        Image img = new Image(product.getImageUrl(), product.getName());
        img.setMaxWidth("100%");
        img.setMaxHeight("100%");
        img.getStyle().set("object-fit", "contain");
        imageContainer.add(img);

        // 2. פרטי המוצר (שם, תיאור והרכב)
        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(compact ? false : true); // פחות רווחים במצב קומפקטי
        infoLayout.setPadding(false);
        infoLayout.setAlignItems(Alignment.CENTER);

        // שם המוצר
        Span name = new Span(product.getName());
        name.getStyle().set("font-weight", "bold")
                   .set("text-align", "center")
                   .set("font-size", compact ? "12px" : "1em"); // גופן קטן יותר בקומפקטי

        infoLayout.add(name);

        // אם אנחנו לא במצב קומפקטי, נציג גם תיאור והרכב בד (חוסך המון גובה למערכת ההמלצות!)
        if (!compact) 
        {
            // תיאור הבגד
            Span description = new Span(product.getDescription());
            description.getStyle().set("font-size", "0.85em").set("color", "#64748b").set("text-align", "center");

            // הרכב הבד
            Span composition = new Span("הרכב: " + product.getTags());
            composition.getStyle().set("font-size", "0.8em").set("color", "#94a3b8").set("font-style", "italic");

            infoLayout.add(description, composition);
        }

        // 3. מחיר - גופן קטן יותר במצב קומפקטי
        Span price = new Span(product.getPrice() / 10.0  + " ₪");
        price.getStyle().set("color", "#2563eb")
                     .set("font-weight", "bold")
                     .set("font-size", compact ? "13px" : "1.2em");

        // 4. כפתור הוספה (מותאם בגודל למצב קומפקטי)
        Button addBtn = new Button(compact ? "הוסף" : "הוסף לסל", VaadinIcon.CART.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        if (compact) {
            addBtn.addThemeVariants(ButtonVariant.LUMO_SMALL); // כפתור קטן וחמוד
        }
        addBtn.setWidthFull();
        addBtn.addClickListener(e -> handleAddToCart(product));

        add(imageContainer, infoLayout, price, addBtn);
    }

    private void handleAddToCart(Product product) 
    {
        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");
        if (currentUser != null) 
        {
            List<Product> cart = (List<Product>) VaadinSession.getCurrent().getAttribute("cart");
            if (cart == null) cart = new ArrayList<>();
            cart.add(product);
            VaadinSession.getCurrent().setAttribute("cart", cart);
            Notification.show(product.getName() + " נוסף לסל");
            
            MainLayout layout = ComponentUtil.getData(UI.getCurrent(), MainLayout.class);
            if (layout != null) layout.refreshCartCount();
        } 
        else 
        {
            UI.getCurrent().navigate("login");
        }
    }
}