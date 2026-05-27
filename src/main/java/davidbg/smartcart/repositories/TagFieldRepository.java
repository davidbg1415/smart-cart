package davidbg.smartcart.repositories;

import davidbg.smartcart.datamodels.TagField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * הסבר על הממשק:
 * ממשק זה מהווה את שכבת הגישה לנתונים עבור ישות מאפייני התגיות.
 * הממשק יורש את יכולות הגישה הבסיסיות למסד הנתונים מ-MongoRepository.
 * @author DAVID BEN GIGI
 */
@Repository
public interface TagFieldRepository extends MongoRepository<TagField, String> 
{
    /* * הממשק מספק גישה לכל פעולות מסד הנתונים הסטנדרטיות (שמירה, מחיקה, עדכון ושליפה)
     * עבור אוסף מאפייני התגיות, ללא צורך בכתיבת שאילתות נוספות.
     */
}