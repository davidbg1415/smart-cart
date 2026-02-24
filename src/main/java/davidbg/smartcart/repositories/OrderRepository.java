package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author DAVID BEN GIGI
 */

@Repository
public interface OrderRepository extends MongoRepository<Order, String> 
{
    
    // שליפת כל ההזמנות של משתמש ספציפי לפי ה-ID שלו
    List<Order> findByUserId(String userId);
}