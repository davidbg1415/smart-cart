package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.services.ProductService;
import davidbg.smartcart.utilities.ProductCard;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("shop")
public class ShopView extends HorizontalLayout 
{

    private final ProductService productService;
    private final FlexLayout productGrid = new FlexLayout();
    private final TextField searchField = new TextField();
    private final VerticalLayout subCategoryContainer = new VerticalLayout();

    private String selectedGender = null;
    private String selectedCategory = null;

    public ShopView(@Autowired ProductService productService) 
    {
        this.productService = productService;

        // הגדרות דף כלליות
        setSizeFull();
        setSpacing(false);
        getStyle().set("background-color", "#f8fafc");

        // 1. יצירת תפריט צד (Sidebar)
        VerticalLayout sidebar = createSidebar();

        // 2. יצירת האזור המרכזי (Header + Grid)
        VerticalLayout mainContent = createMainContent();

        add(sidebar, mainContent);
        setFlexGrow(1, mainContent);

        updateList(); // טעינה ראשונית של כל המוצרים
    }

    private VerticalLayout createSidebar() 
    {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("280px");
        sidebar.setHeightFull();
        sidebar.getStyle().set("background-color", "white").set("border-left", "1px solid #e2e8f0"); // במידה והאתר בעברית.set("padding", "20px");

        H2 title = new H2("SmartCart");
        title.getStyle().set("color", "#1e293b").set("font-size", "1.5em");

        // כפתור "הצג הכל"
        Button allBtn = new Button("כל המוצרים", VaadinIcon.HOME.create(), e -> 
        {
            selectedGender = null;
            selectedCategory = null;
            subCategoryContainer.removeAll();
            updateList();
        });
        allBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        allBtn.setWidthFull();

        // כפתורי מגדר
        Button menBtn = new Button("גברים", VaadinIcon.MALE.create(), e -> showSubCategories("גברים"));
        Button womenBtn = new Button("נשים", VaadinIcon.FEMALE.create(), e -> showSubCategories("נשים"));
        
        menBtn.setWidthFull();
        womenBtn.setWidthFull();

        sidebar.add(title, new Hr(), allBtn, menBtn, womenBtn, new Hr(), subCategoryContainer);
        return sidebar;
    }

    private void showSubCategories(String gender) 
    {
        this.selectedGender = gender;
        this.selectedCategory = null; // איפוס תת-קטגוריה כשבוחרים מגדר חדש
        subCategoryContainer.removeAll();

        H2 subTitle = new H2("קטגוריות " + gender);
        subTitle.getStyle().set("font-size", "1em").set("color", "#64748b");
        subCategoryContainer.add(subTitle);

        if (gender.equals("גברים")) 
        {
            createSubBtn("חולצות");
            createSubBtn("מכנסיים");
            createSubBtn("נעליים");
            createSubBtn("ג'ינס");
        } 
        else 
        {
            createSubBtn("שמלות");
            createSubBtn("חצאיות");
            createSubBtn("חולצות");
            createSubBtn("תיקים");
        }
        updateList();
    }

    private void createSubBtn(String categoryName) 
    {
        Button btn = new Button(categoryName, e -> 
        {
            this.selectedCategory = categoryName;
            updateList();
        });
        btn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        subCategoryContainer.add(btn);
    }

    private VerticalLayout createMainContent() 
    {
        VerticalLayout container = new VerticalLayout();
        container.setPadding(true);

        // שורת חיפוש מעוצבת
        searchField.setPlaceholder("חפש פריט לבוש...");
        searchField.setWidthFull();
        searchField.setMaxWidth("600px");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> updateList());

        // הגדרות הגריד
        productGrid.setFlexWrap(FlexWrap.WRAP);
        productGrid.getStyle().set("gap", "20px");
        productGrid.setJustifyContentMode(JustifyContentMode.CENTER);
        
        // הפיכת הגריד לנגיש לגלילה
        VerticalLayout scrollArea = new VerticalLayout(productGrid);
        scrollArea.setHeightFull();
        scrollArea.getStyle().set("overflow-y", "auto");

        container.add(searchField, scrollArea);
        container.setHorizontalComponentAlignment(Alignment.CENTER, searchField);
        
        return container;
    }

    private void updateList() 
    {
        productGrid.removeAll();
        
        // קריאה ל-Service עם הפרמטרים שבחרנו
        List<Product> products = productService.findFiltered(
                searchField.getValue(), 
                selectedGender, 
                selectedCategory
        );

        for (Product p : products) 
        {
            productGrid.add(new ProductCard(p));
        }
    }
}