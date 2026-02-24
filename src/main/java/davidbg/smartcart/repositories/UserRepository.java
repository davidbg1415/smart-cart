package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author DAVID BEN GIGI
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> 
{
    // ב-UserRepository.java
    User findOneByEmailAndPassword(String email, String password);

}