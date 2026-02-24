package davidbg.smartcart.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import davidbg.smartcart.datamodels.Order;
import davidbg.smartcart.repositories.OrderRepository;

/**
 * @author DAVID BEN GIGI
 */

@Service
public class OrderService 
{
   private OrderRepository orderRepository;

   public OrderService(OrderRepository orderRepository)
   {
      this.orderRepository = orderRepository;
   }

   /// C (Create)
   public void addOrderToDB(Order order)
   {
      orderRepository.insert(order);
   }

   /// R (Read)
   public ArrayList<Order> getOrdersByUserId(String userId)
   {
      return (ArrayList<Order>)orderRepository.findByUserId(userId);
   }

   /// D (Delete)
   public void deleteOrder(String id)
   {
      orderRepository.deleteById(id);
   }
}