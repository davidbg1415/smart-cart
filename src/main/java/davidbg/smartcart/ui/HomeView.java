package davidbg.smartcart.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import davidbg.smartcart.datamodels.Role;
import davidbg.smartcart.datamodels.User;

@Route("") // דף הבית הראשי
@PageTitle("דף הבית | SmartCart")
public class HomeView extends VerticalLayout 
{

    public HomeView() 
    {
        // 1. בדיקה מי המשתמש הנוכחי
        User currentUser = (User) VaadinSession.getCurrent().getAttribute("user");

        // הגדרות עיצוב כלליות לדף
        setAlignItems(Alignment.CENTER);
        setPadding(false);
        setSpacing(false);
        getStyle().set("background", "linear-gradient(to bottom, #ffffff, #f1f5f9)");
        setSizeFull();

        // באנר עליון  
        createHeader(currentUser);

        // תוכן מרכזי
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.CENTER);
        content.setMaxWidth("1000px");

        if (currentUser == null) 
        {
            buildGuestView(content);
        } 
        else 
        {
            buildAuthenticatedView(content, currentUser);
        }

        add(content);
    }

    private void createHeader(User user) 
    {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);
        header.getStyle().set("background-color", "white").set("box-shadow", "0 2px 4px rgba(0,0,0,0.05)");

        H2 logo = new H2("SmartCart");
        logo.getStyle().set("color", "#2563eb").set("margin", "0");

        if (user != null) 
        {
            Button logoutBtn = new Button("התנתק", VaadinIcon.SIGN_OUT.create(), e -> 
            {
                VaadinSession.getCurrent().setAttribute("user", null);
                UI.getCurrent().getPage().reload();
            });
            logoutBtn.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);
            header.add(logo, logoutBtn);
        } 
        else 
        {
            header.add(logo);
        }
        add(header);
    }

    private void buildGuestView(VerticalLayout layout) 
    {
        H1 title = new H1("SmartCart");
        title.getStyle().set("font-size", "3.5em").set("margin-bottom", "0");
        
        H3 subTitle = new H3("המערכת החכמה להתאמת סל קניות אישי");
        subTitle.getStyle().set("color", "#64748b");

        Span creatorInfo = new Span("נוצר ע\"י: דוד בן גיגי");
        creatorInfo.getStyle().set("font-weight", "bold").set("color", "#1e293b").set("margin-bottom", "20px");

        // תמונת הפתיחה שביקשת
        Image welcomeImg = new Image("images/welcome-banner.jpg", "SmartCart");
        welcomeImg.setWidth("550px");
        welcomeImg.getStyle().set("border-radius", "15px").set("box-shadow", "0 10px 20px rgba(0,0,0,0.1)");

        // כפתורי פעולה לאורח
        FlexLayout actions = new FlexLayout();
        actions.getStyle().set("gap", "20px");
        actions.setJustifyContentMode(JustifyContentMode.CENTER);
        actions.getStyle().set("margin-top", "30px");

        Button loginBtn = new Button("התחברות / הרשמה", VaadinIcon.USER.create(), e -> UI.getCurrent().navigate("login"));
        loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);

        Button browseBtn = new Button("צפייה בקטלוג", VaadinIcon.SEARCH.create(), e -> UI.getCurrent().navigate("shop"));
        browseBtn.addThemeVariants(ButtonVariant.LUMO_LARGE);

        actions.add(loginBtn, browseBtn);
        layout.add(title, subTitle, creatorInfo, welcomeImg, actions);
    }

    private void buildAuthenticatedView(VerticalLayout layout, User user) 
    {
        H1 welcome = new H1("ברוך הבא, " + user.getFullName());
        welcome.getStyle().set("margin-top", "40px");
        
        Span info = new Span("מה תרצה לעשות היום?");
        info.getStyle().set("color", "#64748b");

        // גריד כרטיסי ניווט
        FlexLayout menuGrid = new FlexLayout();
        menuGrid.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        menuGrid.setJustifyContentMode(JustifyContentMode.CENTER);
        menuGrid.getStyle().set("gap", "20px").set("padding", "30px");

        // כרטיסים למשתמש רשום
        menuGrid.add(createMenuCard("חנות בגדים", "חיפוש וסינון פריטים ידני", VaadinIcon.SHOP, "shop"));
        menuGrid.add(createMenuCard("סל חכם", "הפעלת אלגוריתם התאמה", VaadinIcon.MAGIC, "smart-cart"));
        menuGrid.add(createMenuCard("היסטוריה", "הזמנות קודמות שלי", VaadinIcon.TIME_BACKWARD, "history"));

        // כרטיסים למנהל (Admin)
        if (user.getRole() == Role.ADMIN) 
        {
            menuGrid.add(createMenuCard("ניהול מוצרים", "הוספה ועריכת פריטים", VaadinIcon.PACKAGE, "admin-products"));
        }

        layout.add(welcome, info, menuGrid);
    }

    private VerticalLayout createMenuCard(String title, String desc, VaadinIcon vIcon, String route) 
    {
        VerticalLayout card = new VerticalLayout();
        card.setWidth("240px");
        card.setAlignItems(Alignment.CENTER);
        card.getStyle().set("background-color", "white")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 6px rgba(0,0,0,0.05)")
                .set("cursor", "pointer")
                .set("padding", "20px");

        Icon icon = vIcon.create();
        icon.setSize("40px");
        icon.setColor("#2563eb");

        Span titleLabel = new Span(title);
        titleLabel.getStyle().set("font-weight", "bold");

        Span descLabel = new Span(desc);
        descLabel.getStyle().set("font-size", "0.85em").set("color", "#64748b").set("text-align", "center");

        card.add(icon, titleLabel, descLabel);
        card.addClickListener(e -> UI.getCurrent().navigate(route));
        
        // אפקט מעבר עכבר
        card.getElement().executeJs("this.addEventListener('mouseenter', function() { this.style.backgroundColor = '#f8fafc'; });");
        card.getElement().executeJs("this.addEventListener('mouseleave', function() { this.style.backgroundColor = 'white'; });");

        return card;
    }
}