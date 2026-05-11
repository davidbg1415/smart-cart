package davidbg.smartcart.utilities;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import davidbg.smartcart.datamodels.Product;

public class ProductCard extends VerticalLayout 
{

    public ProductCard(Product product) 
    {
        // עיצוב הכרטיס
        setSpacing(true);
        setAlignItems(Alignment.CENTER);
        setWidth("220px");
        getStyle().set("border", "1px solid #e2e8f0").set("border-radius", "12px").set("padding", "15px").set("background-color", "white").set("box-shadow", "0 4px 6px -1px rgba(0,0,0,0.1)");

        // תמונת המוצר
        Image img = new Image(product.getImageUrl(), product.getName());
        img.setWidth("180px");
        img.setHeight("180px");
        img.getStyle().set("object-fit", "contain").set("border-radius", "8px");

        // שם המוצר
        Span name = new Span(product.getName());
        name.getStyle().set("font-weight", "600").set("font-size", "1.1em").set("text-align", "center");

        // מחיר (הפיכה מאגורות לשקלים)
        Span price = new Span(String.format("%.2f ₪", product.getPrice() / 10.0));
        price.getStyle().set("color", "#2563eb").set("font-weight", "bold").set("font-size", "1.2em");

        // כפתור הוספה
        Button addBtn = new Button("הוסף לסל", VaadinIcon.CART.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBtn.setWidthFull();

        add(img, name, price, addBtn);
    }
}