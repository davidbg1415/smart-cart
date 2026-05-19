package davidbg.smartcart.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.services.ProductService;

/**
 * טאב ניהול מוצרים - מאפשר להוסיף, למחוק ולערוך את קטלוג הבגדים.
 */
public class AdminProductTab extends VerticalLayout 
{
    private final ProductService productService;
    private final Grid<Product> grid = new Grid<>(Product.class, false);

    public AdminProductTab(ProductService productService) 
    {
        this.productService = productService;
        
        setSizeFull();
        setPadding(true);

        // כותרת וכפתור הוספה
        H3 title = new H3("ניהול קטלוג מוצרים");
        
        Button addBtn = new Button("הוסף מוצר חדש", VaadinIcon.PLUS.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBtn.addClickListener(e -> openProductDialog(null));

        // הגדרת הטבלה
        configureGrid();
        updateGrid();

        add(title, addBtn, grid);
    }

    private void configureGrid() 
    {
        grid.addColumn(Product::getName).setHeader("שם המוצר").setSortable(true);
        
        grid.addColumn(p -> String.format("%.2f ₪", p.getPrice() / 10.0))
            .setHeader("מחיר")
            .setSortable(true);

        // הצגת תגיות (Tags) בטבלה כדי לראות מה האלגוריתם יקרא
        grid.addColumn(p -> p.getTags() != null ? String.join(", ", p.getTags()) : "")
            .setHeader("תגיות");

        // עמודת פעולות: עריכה ומחיקה
        grid.addComponentColumn(product -> 
        {
            Button editBtn = new Button(VaadinIcon.EDIT.create());
            editBtn.addClickListener(e -> openProductDialog(product));

            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteBtn.addClickListener(e -> 
            {
                productService.deleteProduct(product.getId());
                Notification.show("המוצר נמחק בהצלחה");
                updateGrid();
            });

            return new HorizontalLayout(editBtn, deleteBtn);
        }).setHeader("פעולות");
    }

    public void updateGrid() 
    {
        grid.setItems(productService.getAllProducts());
    }

    private void openProductDialog(Product product) 
    {
        // כאן נפתח את הדיאלוג לעריכה/הוספה (נבנה אותו מיד)
        new ProductEditDialog(productService, product, this::updateGrid).open();
    }
}