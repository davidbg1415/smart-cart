package davidbg.smartcart.services;

import davidbg.smartcart.datamodels.TagField;
import davidbg.smartcart.repositories.TagFieldRepository; // צור רפוזיטורי פשוט שמוריש מ-MongoRepository
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagFieldService {
    private final TagFieldRepository repository;

    public TagFieldService(TagFieldRepository repository) {
        this.repository = repository;
    }

    public List<TagField> getAllFields() { return repository.findAll(); }
    public TagField saveField(TagField field) { return repository.save(field); }
    public void deleteField(String id) { repository.deleteById(id); }
}