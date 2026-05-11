package davidbg.smartcart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Product;
import davidbg.smartcart.repositories.ProductRepository;

/**
 * הסבר על המחלקה:
 * מחלקה זו מהווה את שכבת השירות עבור ניהול קטלוג המוצרים במערכת.
 * השירות מאפשר לבצע פעולות לוגיות על מלאי פריטי הלבוש ושליפתם על פי קריטריונים.
 * * @author DAVID BEN GIGI
 */
@Service
public class ProductService 
{
   // ממשק הגישה לנתוני המוצרים במסד הנתונים
   private ProductRepository productRepository;

   /**
    * בנאי המאתחל את השירות:
    * מבצע הזרקה של שכבת הגישה לנתונים לצורך עבודה מול האוסף במונגו.
    * * @param productRepository ממשק הגישה לנתוני המוצרים.
    */
   public ProductService(ProductRepository productRepository)
   {
      this.productRepository = productRepository;
   }

   /**
    * הוספת מוצר חדש למערכת:
    * מקבלת אובייקט של מוצר ושומרת אותו בתוך מסד הנתונים.
    * * @param product אובייקט המוצר המכיל את כל פרטי הפריט.
    * @return ערך בוליאני המאשר את הצלחת הפעולה.
    */
   /// C (Create)
   public boolean addProductToDB(Product product)
   {
      productRepository.insert(product);
      return true;
   }

   /**
    * שליפת כל המוצרים מהמערכת:
    * מחזירה רשימה מלאה של כל פריטי הלבוש הקיימים בקטלוג.
    * * @return רשימה מסוג מערך המכילה את כל אובייקטי המוצר.
    */
   /// R (Read)
   public ArrayList<Product> getAllProducts()
   {
      return (ArrayList<Product>)productRepository.findAll();
   }

   /**
    * חיפוש מוצרים על פי תגית:
    * מחזירה רשימת מוצרים המכילים תגית מסוימת (כמו חורף או אלגנט).
    * פעולה זו חיונית עבור מנוע ההמלצות של המערכת.
    * * @param tag המחרוזת לחיפוש בתוך מערך התגיות של המוצרים.
    * @return רשימה של מוצרים התואמים לתגית המבוקשת.
    */
   public ArrayList<Product> getProductsByTag(String tag)
   {
      return (ArrayList<Product>)productRepository.findByTagsContaining(tag);
   }

   public List<Product> findFiltered(String search, String gender, String category) {
    return productRepository.findAll().stream()
        .filter(p -> (search == null || search.isEmpty() || p.getName().toLowerCase().contains(search.toLowerCase())))
        .filter(p -> (gender == null || p.getTags().contains(gender)))
        .filter(p -> (category == null || p.getTags().contains(category)))
        .collect(Collectors.toList());
}

   /**
    * מחיקת מוצר מהקטלוג:
    * מסירה פריט לבוש ממסד הנתונים על פי המזהה הייחודי שלו.
    * * @param id המזהה הייחודי של המוצר המיועד להסרה.
    */
   /// D (Delete)
   public void deleteProduct(String id)
   {
      productRepository.deleteById(id);
   }
}