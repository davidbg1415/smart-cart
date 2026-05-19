package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.TagField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagFieldRepository extends MongoRepository<TagField, String> 
{
}