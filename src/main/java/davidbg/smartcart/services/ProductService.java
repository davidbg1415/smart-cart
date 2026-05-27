package davidbg.smartcart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.repositories.ProductRepository;

/**
 * שכבת השירות (Service Layer) לניהול קטלוג המוצרים במערכת.
 * מחלקה זו אחראית על ניהול מחזור החיים של מוצרי הלבוש (CRUD), כולל פעולות הוספה, 
 * שליפה, סינון מתקדם ומחיקה מהקטלוג. השירות משמש כמתווך בין הממשק הגרפי (UI) 
 * לבין מסד הנתונים, תוך הוספת לוגיקה עסקית לחיפוש ומיון.
 * @author DAVID BEN GIGI
 */
@Service
public class ProductService 
{
   // ממשק הגישה לנתוני המוצרים במסד הנתונים
   private ProductRepository productRepository;

   /**
    * בנאי המאתחל את השירות:
    * מבצע הזרקה (Dependency Injection) של שכבת הגישה לנתונים לצורך עבודה מול מסד הנתונים.
    * @param productRepository ממשק הגישה לנתוני המוצרים.
    */
   public ProductService(ProductRepository productRepository)
   {
      this.productRepository = productRepository;
   }

   /**
    * הוספת מוצר חדש למערכת (Create):
    * מקבלת אובייקט של מוצר ושומרת אותו בתוך מסד הנתונים.
    * @param product אובייקט המוצר המכיל את כל פרטי הפריט.
    * @return ערך בוליאני המאשר את הצלחת הפעולה.
    */
   public boolean addProductToDB(Product product)
   {
      productRepository.insert(product);
      return true;
   }

   /**
    * שליפת כל המוצרים מהמערכת (Read):
    * מחזירה רשימה מלאה של כל פריטי הלבוש הקיימים בקטלוג.
    * @return רשימה המכילה את כל אובייקטי המוצר.
    */
   public ArrayList<Product> getAllProducts()
   {
      return (ArrayList<Product>)productRepository.findAll();
   }

   /**
    * חיפוש מוצרים על פי תגית:
    * מחזירה רשימת מוצרים המכילים תגית מסוימת (כמו "חורף" או "אלגנט").
    * פעולה זו מהווה חלק מרכזי במנוע ההמלצות ומאפשרת למשתמש לצפות בקטגוריות ממוקדות.
    * @param tag המחרוזת לחיפוש בתוך מערך התגיות של המוצרים.
    * @return רשימה של מוצרים התואמים לתגית המבוקשת.
    */
   public ArrayList<Product> getProductsByTag(String tag)
   {
      return (ArrayList<Product>)productRepository.findByTagsContaining(tag);
   }

   /**
    * ביצוע חיפוש וסינון מתקדם למוצרים:
    * מתודה המשתמשת ב-Java Stream כדי לבצע סינון דינמי של מוצרים לפי שם, מגדר וקטגוריה.
    * הלוגיקה מתבצעת בזמן ריצה כדי לאפשר חיפוש גמיש על פי בחירות המשתמש.
    * @param search מחרוזת טקסט לחיפוש בשם המוצר.
    * @param gender מגדר לסינון.
    * @param category קטגוריה לסינון.
    * @return רשימה מסוננת של מוצרים.
    */
   public List<Product> findFiltered(String search, String gender, String category) 
   {
      return productRepository.findAll().stream()
          /* * לוגיקת הסינון:
           * 1. סינון שם: מוודא שהמחרוזת לא ריקה ובודק הכלה (Case-Insensitive).
           * 2. סינון מגדר/קטגוריה: בודק האם הערך המבוקש קיים בתוך רשימת התגיות (Tags) של המוצר.
           * השימוש ב-Stream מאפשר שרשור (Chaining) של מספר פילטרים בצורה דקלרטיבית.
           */
          .filter(p -> (search == null || search.isEmpty() || p.getName().toLowerCase().contains(search.toLowerCase())))
          .filter(p -> (gender == null || p.getTags().contains(gender)))
          .filter(p -> (category == null || p.getTags().contains(category)))
          .collect(Collectors.toList());
   }

   /**
    * מחיקת מוצר מהקטלוג (Delete):
    * מסירה פריט לבוש ממסד הנתונים על פי המזהה הייחודי שלו.
    * @param id המזהה הייחודי של המוצר המיועד להסרה.
    */
   public void deleteProduct(String id)
   {
      productRepository.deleteById(id);
   }
}