package davidbg.smartcart.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * @author DAVID BEN GIGI
 */

@Route("/") // זה אומר שזה הדף הראשי (localhost:8080)
public class MainView extends AppLayout
{
    public MainView()
    {
        createHeader();
        createMainContent();
    }

    private void createHeader() 
    {
        // 1. לוגו
        H1 logo = new H1("SmartCart 🛒");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);

        // 2. שורת חיפוש
        TextField searchField = new TextField();
        searchField.setPlaceholder("חפש מוצר...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addClassName(LumoUtility.Margin.End.AUTO); // דוחף את האלמנטים הבאים לצד שמאל

        // 3. כפתור סל קניות 
        Button cartBtn = new Button("סל (0)", VaadinIcon.CART.create());
        cartBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // 4. אווטאר למשתמש
        Avatar avatar = new Avatar("David Ben Gigi");
        avatar.addClassName(LumoUtility.Margin.Horizontal.SMALL);

        // הרכבת הבר העליון
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, searchField, cartBtn, avatar);
        header.setDefaultVerticalComponentAlignment(FlexLayout.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Padding.Vertical.NONE,LumoUtility.Padding.Horizontal.MEDIUM,LumoUtility.BoxShadow.SMALL);

        addToNavbar(header);
    }

    private void createMainContent() 
    {
        H2 welcomeTitle = new H2("ברוכים הבאים ל-SmartCart!");
        Paragraph subtitle = new Paragraph("הדרך החכמה לקנות לפי התקציב שלך. תן לאלגוריתם שלנו לעבוד בשבילך.");
        
        Button smartButton = new Button("צור סל חכם 🤖");
        smartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        
        Button catalogButton = new Button("לקטלוג המלא");
        catalogButton.addThemeVariants(ButtonVariant.LUMO_LARGE);

        HorizontalLayout buttonsLayout = new HorizontalLayout(smartButton, catalogButton);
        
        VerticalLayout heroSection = new VerticalLayout(welcomeTitle, subtitle, buttonsLayout);
        heroSection.setAlignItems(FlexLayout.Alignment.CENTER);
        heroSection.addClassNames(
            LumoUtility.Background.CONTRAST_5, // רקע אפור בהיר מאוד
            LumoUtility.Padding.LARGE,
            LumoUtility.BorderRadius.LARGE,
            LumoUtility.Margin.Bottom.LARGE
        );

        // --- חלק 2: גריד המוצרים (דמה) ---
        H3 productsTitle = new H3("מוצרים חמים עכשיו 🔥");
        
        // נשתמש ב-FlexLayout כדי שהמוצרים יסתדרו אחד ליד השני ויעברו שורה כשאין מקום
        FlexLayout productGrid = new FlexLayout();
        productGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        
        
        // יצירת מוצרים פיקטיביים לתצוגה
        productGrid.add(createProductCard("iPhone 15", "₪ 4,000", "https://placehold.co/150x150?text=iPhone"));
        productGrid.add(createProductCard("חלב תנובה 3%", "₪ 7.90", "https://placehold.co/150x150?text=Milk"));
        productGrid.add(createProductCard("קוקה קולה 1.5L", "₪ 8.50", "https://placehold.co/150x150?text=Cola"));
        productGrid.add(createProductCard("לחם אחיד", "₪ 6.90", "https://placehold.co/150x150?text=Bread"));
        productGrid.add(createProductCard("ביצים L", "₪ 14.90", "https://placehold.co/150x150?text=Eggs"));

        // הרכבת הדף כולו
        VerticalLayout mainContainer = new VerticalLayout(heroSection, productsTitle, productGrid);
        mainContainer.setSizeFull();
        
        setContent(mainContainer);
    }

    // פונקציית עזר ליצירת כרטיס מוצר מעוצב
    private VerticalLayout createProductCard(String name, String price, String imageUrl) 
    {
        Image image = new Image(imageUrl, name);
        image.setWidth("100%");
        image.setHeight("150px");
        image.addClassName(LumoUtility.BorderRadius.MEDIUM); // פינות עגולות לתמונה

        Span nameSpan = new Span(name);
        nameSpan.addClassName(LumoUtility.FontWeight.BOLD);
        
        Span priceSpan = new Span(price);
        priceSpan.addClassName(LumoUtility.TextColor.SECONDARY);

        Button addBtn = new Button("הוסף לסל", VaadinIcon.PLUS.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);
        addBtn.setWidthFull();

        VerticalLayout card = new VerticalLayout(image, nameSpan, priceSpan, addBtn);
        card.setSpacing(false);
        card.setPadding(true);
        card.setWidth("200px"); // רוחב קבוע לכרטיס
        
        // עיצוב הכרטיס (מסגרת וצל)
        card.addClassNames(
            LumoUtility.Border.ALL,
            LumoUtility.BorderColor.CONTRAST_10,
            LumoUtility.BorderRadius.MEDIUM,
            LumoUtility.BoxShadow.SMALL
        );
        
        return card;
    }
    
}
