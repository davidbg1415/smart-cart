package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author DAVID BEN GIGI
 */

@Repository
public interface ProductRepository extends MongoRepository<Product, String> 
{
    
    // מציאת מוצרים המכילים תגית מסוימת (למשל "Winter")
    List<Product> findByTagsContaining(String tag);
}