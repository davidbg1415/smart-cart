package davidbg.smartcart.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.repositories.OrderRepository;

/**
 * הסבר על המחלקה:
 * מחלקה זו מהווה את שכבת השירות עבור ניהול ההזמנות במערכת.
 * השירות מקשר בין הלוגיקה העסקית של האפליקציה לבין שכבת הגישה לנתונים.
 * * @author DAVID BEN GIGI
 */
@Service
public class OrderService 
{
   // ממשק הגישה לנתוני ההזמנות במסד הנתונים
   private OrderRepository orderRepository;

   /**
    * בנאי המאתחל את השירות:
    * מבצע הזרקה של שכבת הגישה לנתונים לצורך עבודה מול מסד הנתונים.
    * * @param orderRepository ממשק הגישה לנתוני ההזמנות.
    */
   public OrderService(OrderRepository orderRepository)
   {
      this.orderRepository = orderRepository;
   }

   /**
    * יצירת הזמנה חדשה:
    * מקבלת אובייקט של הזמנה ושומרת אותו באופן קבוע במסד הנתונים.
    * * @param order אובייקט ההזמנה המכיל את כל פרטי הרכישה.
    */
   /// C (Create)
   public void addOrderToDB(Order order)
   {
      orderRepository.insert(order);
   }

   /**
    * שליפת הזמנות לפי משתמש:
    * מחזירה רשימה של כל ההזמנות שבוצעו על ידי משתמש מסוים.
    * * @param userId המזהה הייחודי של המשתמש המבוקש.
    * @return רשימה של הזמנות השייכות למשתמש זה.
    */
   /// R (Read)
   public ArrayList<Order> getOrdersByUserId(String userId)
   {
      return (ArrayList<Order>)orderRepository.findByUserId(userId);
   }

   /**
    * מחיקת הזמנה מהמערכת:
    * מסירה לצמיתות הזמנה ממסד הנתונים לפי המזהה שלה.
    * * @param id המזהה הייחודי של ההזמנה המיועדת למחיקה.
    */
   /// D (Delete)
   public void deleteOrder(String id)
   {
      orderRepository.deleteById(id);
   }
}